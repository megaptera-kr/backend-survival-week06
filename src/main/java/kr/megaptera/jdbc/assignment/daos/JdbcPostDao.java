package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.domains.model.Post;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcPostDao implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 공통 코드 모듈화를 충분히 실행하고 싶지만 시간 여유가 없음..

    @Override
    public List<PostDto> findAll() {
        String query = "SELECT * FROM posts";
        List<PostDto> posts = new ArrayList<>();
        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");

                PostDto postDto = new PostDto(id, title, author, content);

                posts.add(postDto);
            }
        });
        return posts;
    }

    @Override
    public PostDto find(String id) {
        String query = "SELECT * FROM posts WHERE id = ?";
        return jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next())
                throw new PostNotFound();
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");

            return new PostDto(id, title, author, content);
        }, id);
    }

    @Override
    public void save(Post post) {
        String query = "INSERT INTO posts(id, title, author, content)" +
                " VALUES(?, ?, ?, ?)";
        int result = jdbcTemplate.update(query,
                post.id().getId(), post.title().getTitle(), post.author().getAuthor(), post.content().getContent());
    }

    @Override
    public void update(String id, Post post) {
        String query = "UPDATE posts" +
                " SET content = ? AND title = ?" +
                " WHERE id = ?";
        int result = jdbcTemplate.update(query, post.content().getContent(), post.title().getTitle(), id);
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM posts" +
                " WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
