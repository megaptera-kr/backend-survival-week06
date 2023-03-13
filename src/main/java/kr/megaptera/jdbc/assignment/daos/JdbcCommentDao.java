package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.comment.*;
import kr.megaptera.jdbc.assignment.models.post.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class JdbcCommentDao implements CommentDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> findAll(String postId) {
        List<Comment> list = new ArrayList<>();

        String query = """
                SELECT * FROM comments WHERE postId=?
                """;

        jdbcTemplate.query(query, rs -> {
            while (rs.next()) {
                String id = rs.getString("id");
                String author = rs.getString("author");
                String content = rs.getString("content");
                list.add(new Comment(
                        CommentId.of(id),
                        PostId.of(postId),
                        author,
                        content));
            }
            return null;
        }, postId);

        return list;
    }

    @Override
    public Comment find(String id, String postId) {
        String query = """
                SELECT * FROM comments WHERE id=? AND postId=?
                """;
        Comment found = jdbcTemplate.query(query, rs -> {
                    if (!rs.next()) {
                        throw new CommentNotFound();
                    }

                    Comment comment = new Comment(
                            CommentId.of(rs.getString("id")),
                            PostId.of(rs.getString("postId")),
                            rs.getString("author"),
                            rs.getString("content"));

                    return comment;
                },
                id,
                postId);

        return found;
    }

    @Override
    public void save(Comment comment) {
        if (comment.commentId() == null) {
            insertComment(comment);
            return;
        }
        updateComment(comment);
    }

    @Override
    public void delete(Comment comment) {
        String query = """
                DELETE FROM comments WHERE id=?
                """;
        jdbcTemplate.update(
                query,
                comment.commentId().toString());
    }

    private void updateComment(Comment comment) {
        String query = """
                UPDATE comments SET content=? WHERE id=?
                """;
        jdbcTemplate.update(query,
                comment.content(),
                comment.commentId().toString());
    }

    private void insertComment(Comment comment) {
        String query = """
                INSERT INTO comments(id, postId, author, content) VALUES(?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                query,
                CommentId.generate().toString(),
                comment.postId().toString(),
                comment.author(),
                comment.content());
    }
}
