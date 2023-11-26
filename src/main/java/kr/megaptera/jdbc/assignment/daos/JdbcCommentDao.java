package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentAuthor;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Component
public class JdbcCommentDao implements CommentDao {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public List<Comment> findAll(String targetPostId) {
        String query = "SELECT * FROM comment WHERE post_id = ?";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String content = resultSet.getString("content");
            String author = resultSet.getString("author");
            int postId = resultSet.getInt("post_id");
            return new Comment(CommentId.of(id), PostId.of(postId), CommentAuthor.of(author), MultilineText.of(content));
        }, Integer.parseInt(targetPostId));
    }

    @Override
    public Comment find(CommentId commentId) {
        String query = "SELECT * FROM comment WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String content = resultSet.getString("content");
            String author = resultSet.getString("author");
            int postId = resultSet.getInt("post_id");
            return new Comment(CommentId.of(id), PostId.of(postId), CommentAuthor.of(author), MultilineText.of(content));
        }, commentId.toInt());
    }

    @Override
    public void save(Comment comment) {
        transactionTemplate.execute(status -> {
            String query = "INSERT INTO comment (content, author, post_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(query,
                    comment.content().toString(),
                    comment.author().toString(),
                    comment.postId().toInt());
            return null;
        });
    }

    @Override
    public void delete(CommentId commentId) {
        String query = "DELETE FROM comment WHERE id = ?";
        jdbcTemplate.update(query, commentId.toInt());
    }

    @Override
    public void clear() {
        String query = "DELETE FROM comment";
        jdbcTemplate.update(query);
    }

    @Override
    public void update(Comment comment) {
        transactionTemplate.execute(status -> {
            String updateQuery = "UPDATE comment SET content = ? WHERE id = ?";
            jdbcTemplate.update(updateQuery,
                    comment.content().toString(),
                    comment.id().toInt());
            return null;
        });
    }
}
