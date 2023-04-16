package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class JdbcPostDao implements PostDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {
        String query = "SELECT * FROM posts";
        List<Post> posts = jdbcTemplate.query(query, (resultSet, rowNum) -> {
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");
            Post post = new Post(PostId.of(id), title, author, content);
            return post;
        });
        return posts;
    }


    @Override
    public Post find(String id) {
        String query = "SELECT * from posts WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
                String postId = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                return new Post(PostId.of(postId), title, author, content);
            }, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PostNotFound();
        }
    }

    @Override
    public void create(Post post) {
        String query = "INSERT INTO posts (id, title, author, content) VALUES(?,?,?,?)";
        jdbcTemplate.update(query, post.getId().toString(), post.getTitle(), post.getAuthor(), post.getContent());
    }

    @Override
    public void update(String id, PostUpdateDto postUpdateDto) {
        String query = "UPDATE posts SET title = ?, content =? WHERE id = ?";
        jdbcTemplate.update(query, postUpdateDto.getTitle(), postUpdateDto.getContent(), id);
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

}
