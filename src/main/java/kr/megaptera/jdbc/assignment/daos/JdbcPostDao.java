package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.post.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Component
public class JdbcPostDao implements PostDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        String query = "SELECT * FROM posts";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                Post post = extractPost(resultSet);
                posts.add(post);
            }
            return null;
        });

        return posts;
    }

    @Override
    public Post find(String id) {
        String query = "SELECT * FROM posts WHERE id=?";

        Post post = jdbcTemplate.query(query, rs -> {
            if (!rs.next()) {
                throw new PostNotFound();
            }
            return extractPost(rs);
        }, id);

        return post;
    }

    private Post extractPost(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        String content = rs.getString("content");

        return new Post(PostId.of(id),
                title,
                author,
                MultilineText.of(content));
    }

    @Override
    public void save(Post post) {
        if (post.id() == null) {
            insertPost(post);
            return;
        }
        updatePost(post);
    }

    @Override
    public void delete(PostId postId) {
        String query = "DELETE FROM posts WHERE id=?";

        jdbcTemplate.update(query, postId.toString());
    }

    private void updatePost(Post post) {
        String query = """
                UPDATE posts SET title=?, content=? WHERE id=?
                """;
        jdbcTemplate.update(query,
                post.title(),
                post.content().toString(),
                post.id().toString());
    }

    private void insertPost(Post post) {
        String query = """
                INSERT INTO posts (id, title, author, content) VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(query,
                PostId.generate().toString(),
                post.title(),
                post.author(),
                post.content().toString());
    }
}
