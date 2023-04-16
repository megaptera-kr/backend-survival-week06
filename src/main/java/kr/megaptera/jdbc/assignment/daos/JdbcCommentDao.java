package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcCommentDao implements CommentDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CommentEntity> findByPostId(String postId) {
        List<CommentEntity> comments = new ArrayList<>();

        String query = "SELECT * FROM comments WHERE post_id=?";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                CommentEntity comment = extractComment(resultSet);
                comments.add(comment);
            }

            return null;
        }, postId.toString());

        return comments;
    }

    public CommentEntity find(String postId) {
        String query = "SELECT * FROM comments WHERE id=?";

        CommentEntity found = jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next()) {
                throw new CommentNotFoundException();
            }

            CommentEntity comment = extractComment(resultSet);

            return comment;
        }, postId);

        return found;
    }

    private CommentEntity extractComment(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String postId = resultSet.getString("post_id");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");

        CommentEntity comment = new CommentEntity(
                id,
                postId,
                author,
                content
        );

        return comment;
    }

    public void add(CommentEntity comment) {
        String query = """
            INSERT INTO comments (id, post_id, author, content) VALUES (?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                query,
                comment.getId(),
                comment.getPostId(),
                comment.getAuthor(),
                comment.getContent()
        );
    }

    public void update(CommentEntity comment) {
        String query = """
            UPDATE comments SET content=? WHERE id=?
            """;

        jdbcTemplate.update(
                query,
                comment.getContent(),
                comment.getId()
        );;
    }

    public void remove(CommentEntity comment) {
        String query = "DELETE FROM comments WHERE id=?";
        jdbcTemplate.update(query, comment.getId());
    }
}
