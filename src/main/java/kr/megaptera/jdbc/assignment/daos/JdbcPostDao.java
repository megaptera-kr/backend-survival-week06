package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.domains.model.Post;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 공통 코드 모듈화를 충분히 실행하고 싶지만 시간 여유가 없음..

    @Override
    public List<PostDto> findAll() {
        String query = "SELECT * FROM posts";

        return jdbcTemplate.query(query, resultSet -> {
            List<PostDto> newPosts = new ArrayList<>();
            while (resultSet.next()) {
                PostDto postDto = getDataFromRow(resultSet);
                newPosts.add(postDto);
            }
            return newPosts;
        });
    }

    @Override
    public PostDto find(String id) {
        String query = "SELECT * FROM posts WHERE id = ?";
        return jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next())
                throw new PostNotFound();

            return getDataFromRow(resultSet);
        }, id);
    }

    @Override
    public void save(Post post) {
        String query = "INSERT INTO posts(id, title, author, content)" +
                " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(query,
                post.id().getId(), post.title().getTitle(), post.author().getAuthor(), post.content().toString());
    }

    @Override
    public void update(String id, Post post) {
        String query = "UPDATE posts" +
                " SET content = ?, title = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(query, post.content().toString(), post.title().getTitle(), id);
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM posts" +
                " WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    private PostDto getDataFromRow(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");
        return new PostDto(id, title, author, content);
    }
}
