package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentContent;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class JdbcCommentDao implements CommentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TransactionTemplate transactionTemplate;

    @Override
    public List<Comment> findAll(PostId postId) {
        String query = "SELECT * FROM comments where postid = ?";
        List<Comment> comments = jdbcTemplate.query(query, (resultSet, num) -> {
            String id = resultSet.getString("id");
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");
            String dbPostId = resultSet.getString("postId");
            Comment comment = new Comment(CommentId.of(id), author, CommentContent.of(content), PostId.of(dbPostId));
            return comment;
        }, postId.toString());
        return comments;
    }


    @Override
    public void save(Comment comment, PostId postId) {
        transactionTemplate.execute(status -> {
            String query = "insert into comments values(?, ?, ?, ?)";
            jdbcTemplate.update(query, comment.id().toString(), comment.author(), comment.content().toString(), postId.toString());
            return status;
        });
    }

    @Override
    public void update(String id, Comment comment, PostId postId) {
        String query = "update comments set content = ? where id = ? and postid = ?";
        jdbcTemplate.update(query, comment.content().toString(), id, postId.toString());
        System.out.println("update success");
    }

    @Override
    public void delete(String id, PostId postId) {
        transactionTemplate.execute(status -> {
            String query = "delete from comments where id = ?";
            jdbcTemplate.update(query, id);
            return status;
        });
        System.out.println("jdbcdao delete success");
    }

    @Override
    public Comment findComment(String id, PostId postId) {
        String query = "SELECT * FROM comments WHERE id = ? and postid = ?";
        Comment comment;
        return jdbcTemplate.queryForObject(query, (rs, num) -> {
            String commentId = rs.getString("id");
            String author = rs.getString("author");
            String content = rs.getString("content");
            String dbPostId = rs.getString("postId");
            return new Comment(CommentId.of(commentId), author, CommentContent.of(content), PostId.of(dbPostId));
        }, id, postId.toString());
    }
}
