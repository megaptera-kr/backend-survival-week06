package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFoundException;
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

    @Override
    public List<PostEntity> findAll() {
        List<PostEntity> posts = new ArrayList<>();

        String query = "SELECT * FROM posts";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                var post = extractPost(resultSet);
                posts.add(post);
            }

            return null;
        });

        return posts;
    }

    @Override
    public PostEntity find(String id) {
        String query = "SELECT * FROM posts WHERE id=?";

        var found = jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next()) {
                throw new PostNotFoundException();
            }

            var post = extractPost(resultSet);

            return post;
        }, id.toString());

        return found;
    }

    private PostEntity extractPost(ResultSet resultSet) throws SQLException {
        String postId = resultSet.getString("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");

        PostEntity post = new PostEntity(
                postId,
                title,
                author,
                content
        );

        return post;
    }

    @Override
    public void add(PostEntity post){
        String query = """
            INSERT INTO posts (id, title, author, content) VALUES (?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                query,
                post.getId(),
                post.getTitle(),
                post.getAuthor(),
                post.getContent()
        );
    }

    @Override
    public void update(String id, PostEntity post){
        String query = """
            UPDATE posts SET title=?, content=? WHERE id=?
            """;

        jdbcTemplate.update(
                query,
                post.getTitle(),
                post.getContent(),
                post.getId()
        );
    }

    @Override
    public void remove(String id){
        String query = "DELETE FROM posts WHERE id=?";

        jdbcTemplate.update(query, id.toString());
    }
}
