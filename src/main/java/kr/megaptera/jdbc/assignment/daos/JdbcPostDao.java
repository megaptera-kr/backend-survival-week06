package kr.megaptera.jdbc.assignment.daos;

import java.util.List;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcPostDao implements PostDao {

    private final JdbcTemplate jdbcTemplate;
    private Long id;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.id = 0L;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == null) {
            generateId();
            post.generateId(id);
        }

        String query = """
                INSERT INTO posts(id, title, author, content)
                VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(query, post.getId(), post.getTitle(), post.getAuthor(),
                post.getContent());
    }

    private void generateId() {
        this.id++;
    }

    @Override
    public List<Post> findAll() {
        String query = """
                SELECT * FROM posts;
                """;
        return jdbcTemplate.query(query, postRowMapper());
    }

    @Override
    public Post findById(String id) {
        String query = """
                SELECT * FROM posts
                WHERE id = ?
                """;

        return jdbcTemplate.queryForObject(query, postRowMapper(), id);
    }

    @Override
    public void update(String id, String title, String content) {
        String query = """
                UPDATE posts set title = ?, content = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(query, title, content, id);
    }

    @Override
    public void delete(String id) {
        String query = """
                DELETE FROM posts
                WHERE id = ?
                """;
        jdbcTemplate.update(query, id);
        degenerateId();
    }

    private void degenerateId() {
        if (this.id > 0) {
            this.id--;
        }
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) ->
                new Post(rs.getString("id"), rs.getString("title"),
                        rs.getString("author"), rs.getString("content"));

    }

    @Override
    public void clean() {
        String query = """
                DELETE FROM posts;
                """;
        jdbcTemplate.execute(query);
        resetId();
    }

    private void resetId() {
        this.id = 0L;
    }
}
