package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.PostDto;
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

    private PostDto transform(ResultSet rs) throws SQLException {
        return new PostDto(rs.getString("id")
                , rs.getString("title")
                , rs.getString("author")
                , rs.getString("content"));
    }

    @Override
    public List<PostDto> getList() {
        List<PostDto> posts = new ArrayList<>();

        String sql = "SELECT * FROM POSTS";

        jdbcTemplate.query(sql, resultSet -> {
            while (resultSet.next()) {
                posts.add(transform(resultSet));
            }
        });

        return posts;
    }

    @Override
    public PostDto getPost(PostDto postDto) {
        String sql = """
                SELECT *
                  FROM POSTS
                 WHERE ID = ?
                """;

        PostDto findOne = jdbcTemplate.query(sql, rs -> {
            if (!rs.next()) throw new SQLException("Not Found");

            return transform(rs);
        }, postDto.getId());
        return findOne;
    }

    @Override
    public void insertPost(PostDto postDto) {
        String sql = """
                INSERT INTO POSTS ( ID, TITLE, AUTHOR, CONTENT)
                           VALUES ( ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql, postDto.getId(), postDto.getTitle(), postDto.getAuthor(), postDto.getContent());
    }

    @Override
    public void updatePost(PostDto postDto) {
        String sql = """
                UPDATE POSTS
                   SET TITLE = ?
                      ,CONTENT = ?
                 WHERE ID = ?
                """;
        jdbcTemplate.update(sql, postDto.getTitle(), postDto.getContent(), postDto.getId());
    }

    @Override
    public void deletePost(PostDto postDto) {
        String sql = """
                DELETE FROM POSTS
                 WHERE ID = ?
                """;
        jdbcTemplate.update(sql, postDto.getId());
    }
}
