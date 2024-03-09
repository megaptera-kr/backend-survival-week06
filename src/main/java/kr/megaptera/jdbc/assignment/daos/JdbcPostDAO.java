package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.PostNotFoundException;
import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.model.PostId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class JdbcPostDAO implements PostDAO {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public JdbcPostDAO(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public List<Post> findAllPosts() {
        String sql = "SELECT * FROM posts";
        return jdbcTemplate.query(sql, postRowMapper());
    }

    @Override
    public Post findPostById(PostId id) {
        long postIdValue = id.value();
        String sql = "SELECT * FROM posts WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, postRowMapper(), postIdValue);
        } catch (EmptyResultDataAccessException e) {
            throw new PostNotFoundException("Invalid ID");
        }
    }

    @Override
    public void savePost(Post post) {
        transactionTemplate.execute(status -> {
            String sql = "INSERT INTO posts (id, author, title, content) VALUES (?, ?, ? ,?)";
            jdbcTemplate.update(sql, post.id().value(), post.author(), post.title(), post.content());
            return null;
        });
    }

    @Override
    public void updatePost(Post post) {
        if (!isPostIdValid(String.valueOf(post.id().value()))) {
            throw new PostNotFoundException("Invalid ID");
        }

        transactionTemplate.execute(status -> {
            String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
            jdbcTemplate.update(sql, post.title(), post.content(), post.id().value());
            return null;
        });
    }

    @Override
    public void deletePost(PostId postId) {
        if (!isPostIdValid(String.valueOf(postId.value()))) {
            throw new PostNotFoundException("Invalid ID");
        }

        transactionTemplate.execute(status -> {
            String sql = "DELETE FROM posts WHERE id = ?";
            jdbcTemplate.update(sql, postId.value());
            return null;
        });
    }

    private boolean isPostIdValid(String postId) {
        String sql = "SELECT COUNT(*) FROM posts WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, Long.parseLong(postId));
        assert count != null;
        return count.compareTo(0) > 0;
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> new Post(
                new PostId(rs.getLong("id")),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("content")
        );
    }
}
