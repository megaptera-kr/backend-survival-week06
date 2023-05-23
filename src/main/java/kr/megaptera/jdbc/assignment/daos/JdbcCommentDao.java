package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Component
public class JdbcCommentDao implements CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> findAll(PostId postId) {
        List<Comment> comments = new ArrayList<>();

        String query = "SELECT * FROM comments WHERE post_id=?";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                Comment comment = extractComment(resultSet);
                comments.add(comment);
            }

            return null;
        }, postId.toString());

        return comments;
    }

    @Override
    public Comment find(CommentId id, PostId postId) {
        String query = "SELECT * FROM comments WHERE id=? AND post_id=?";

        Comment found = jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next()) {
                try {
                    throw new CommentNotFound();
                } catch (CommentNotFound e) {
                    throw new RuntimeException(e);
                }
            }

            Comment comment = extractComment(resultSet);

            return comment;
        }, id.toString(), postId.toString());

        return found;
    }

    @Override
    public void save(Comment comment) {
        if (comment.id() != null) {
            updateComment(comment);
            return;
        }

        insertComment(comment);
    }

    @Override
    public void delete(CommentId id) {
        String query = "DELETE FROM comments WHERE id=?";

        jdbcTemplate.update(query, id.toString());
    }

    private Comment extractComment(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String postId = resultSet.getString("post_id");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");

        Comment comment = new Comment(
                CommentId.of(id),
                PostId.of(postId),
                author,
                content
        );

        return comment;
    }

    private void insertComment(Comment comment) {
        String query = """
            INSERT INTO comments (id, post_id, author, content) VALUES (?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                query,
                CommentId.generate().toString(),
                comment.postId().toString(),
                comment.author(),
                comment.content()
        );
    }

    private void updateComment(Comment comment) {
        String query = """
            UPDATE comments SET content=? WHERE id=?
            """;

        jdbcTemplate.update(
                query,
                comment.content(),
                comment.id().toString()
        );
    }
}
