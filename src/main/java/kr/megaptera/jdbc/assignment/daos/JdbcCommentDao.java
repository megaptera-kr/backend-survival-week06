package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
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
    public List<Comment> findAll(PostId postId) {
        List<Comment> comments = new ArrayList<>();

        String query = "SELECT * FROM testcomment WHERE postid = ?";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                Comment comment = new Comment(
                        CommentId.of(id),
                        postId,
                        author,
                        MultilineText.of(content));

                comments.add(comment);
            }
        }, postId.toString());

        return comments;
    }

    @Override
    public Comment find(CommentId id) {
        String query = "SELECT * FROM testcomment WHERE id = ?";

        Comment comment = jdbcTemplate.query(query, resultSet -> {
            String resId = resultSet.getString("id");
            String postid = resultSet.getString("postid");
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");

            return new Comment(
                    CommentId.of(resId),
                    PostId.of(postid),
                    author,
                    MultilineText.of(content));

        }, id.toString());

        return comment;
    }

    @Override
    public void save(Comment comment) {

        transactionTemplate.execute(status -> {
            String sql = "INSERT INTO testcomment(id, postid, author, content) VALUES(?, ?, ?, ?) ";

            jdbcTemplate.update(
                    sql,
                    comment.id(),
                    comment.postId().toString(),
                    comment.author(),
                    comment.content().toString()
            );

            return null;
        });
    }

    @Override
    public void delete(CommentId id) {
        transactionTemplate.execute(status -> {
            String sql = "DELETE FROM testcomment WHERE id = ?";

            jdbcTemplate.update(
                    sql,
                    id.toString()
            );

            return null;
        });
    }
}
