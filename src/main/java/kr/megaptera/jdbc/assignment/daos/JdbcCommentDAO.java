package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFoundException;
import kr.megaptera.jdbc.assignment.model.Comment;
import kr.megaptera.jdbc.assignment.model.CommentId;
import kr.megaptera.jdbc.assignment.model.PostId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class JdbcCommentDAO implements CommentDAO {

    private final Logger logger = LoggerFactory.getLogger(JdbcCommentDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public JdbcCommentDAO(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void saveComment(Comment comment) {
        String postId = comment.postId().toString();
        if (!isPostIdValid(postId)) {
            throw new PostNotFoundException("Invalid post ID : " + postId);
        }

        transactionTemplate.execute(status -> {
            String sql = "INSERT INTO comments (id, post_id, author, content) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, comment.id().value(), comment.postId().value(), comment.author(), comment.content());
            return null;
        });
    }

    @Override
    public void updateComment(String postId, String commentId, String content) {
        if (!isPostIdValid(postId)) {
            throw new PostNotFoundException("Invalid post ID : " + postId);
        }
        if (!isCommentIdValid(commentId)) {
            throw new CommentNotFoundException("Invalid comment ID : " + commentId);
        }
        transactionTemplate.execute(status -> {
            String sql = "UPDATE comments SET content = ? WHERE post_id = ? AND id = ?";
            jdbcTemplate.update(sql, content, Long.parseLong(postId), Long.parseLong(commentId));
            return null;
        });
    }

    @Override
    public void deleteComment(String postId, String commentId) {
        if (!isPostIdValid(postId)) {
            throw new PostNotFoundException("Invalid post ID : " + postId);
        }
        if (!isCommentIdValid(commentId)) {
            throw new CommentNotFoundException("Invalid comment ID : " + commentId);
        }
        transactionTemplate.execute(status -> {
            String sql = "DELETE FROM comments WHERE post_id = ? AND id = ?";
            jdbcTemplate.update(sql, Long.parseLong(postId), Long.parseLong(commentId));
            return null;
        });
    }

    @Override
    public List<Comment> findComments(String postId) {
        if (!isPostIdValid(postId)) {
            throw new PostNotFoundException("Invalid post ID");
        }
        String sql = "SELECT * FROM comments WHERE post_id = ?";
        return jdbcTemplate.query(sql, commentRowMapper(), Long.parseLong(postId));
    }

    private boolean isCommentIdValid(String commentId) {
        String sql = "SELECT COUNT(*) FROM comments WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, Long.parseLong(commentId));
        assert count != null;
        return count.compareTo(0) > 0;
    }

    private boolean isPostIdValid(String postId) {
        String sql = "SELECT COUNT(*) FROM posts WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, Long.parseLong(postId));
        assert count != null;
        return count.compareTo(0) > 0;
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> new Comment(
                CommentId.of(rs.getLong("id")),
                PostId.of(rs.getLong("post_id")),
                rs.getString("author"),
                rs.getString("content")
        );
    }
}
