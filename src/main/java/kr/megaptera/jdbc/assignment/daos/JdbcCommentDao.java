package kr.megaptera.jdbc.assignment.daos;

import java.util.List;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcCommentDao implements CommentDao {

    private final JdbcTemplate jdbcTemplate;
    private Long id;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.id = 0L;
    }


    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            generateId();
            comment.generateId(id);
        }

        String query = """
                INSERT INTO comments(id, post_id, author, content)
                VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(query, comment.getId(), comment.getPostId(), comment.getAuthor(),
                comment.getContent());

        return comment;
    }

    private void generateId() {
        this.id++;
    }

    @Override
    public List<Comment> findByPostId(String postId) {
        String query = """
                SELECT * FROM comments
                WHERE post_id = ?
                """;

        return jdbcTemplate.query(query, commentRowMapper(), postId);
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) ->
                new Comment(rs.getString("id"), rs.getString("post_id"),
                        rs.getString("author"), rs.getString("content"));

    }

    @Override
    public Comment find(String id, String postId) {
        String query = """
                SELECT * FROM comments
                WHERE id = ? and post_id = ?
                """;
        return jdbcTemplate.queryForObject(query, commentRowMapper(), id, postId);
    }

    @Override
    public void update(String id, String postId, String content) {
        String query = """
                UPDATE comments set content = ?
                WHERE id = ? and post_id = ?
                """;
        jdbcTemplate.update(query, content, id, postId);
    }

    @Override
    public void delete(String id, String postId) {
        String query = """
                DELETE FROM comments
                WHERE id = ? and post_id = ?
                """;
        jdbcTemplate.update(query, id, postId);
        degenerateId();
    }

    private void degenerateId() {
        if (this.id > 0) {
            this.id--;
        }
    }

    @Override
    public void clear() {
        String query = """
                DELETE FROM comments;
                """;
        jdbcTemplate.execute(query);
        resetId();
    }

    private void resetId() {
        this.id = 0L;
    }
}
