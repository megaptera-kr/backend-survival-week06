package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcPostDao implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    private final TransactionTemplate transactionTemplate;

    @Override
    public List<Post> findAll() {
//        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM posts";
//        jdbcTemplate.query(query, resultSet -> {
//            while (resultSet.next()) {
//                PostId id = PostId.of(resultSet.getString("id"));
//                String title = resultSet.getString("title");
//                String author = resultSet.getString("author");
//                String content = resultSet.getString("content");
//
//                Post post = new Post(id, title, author, content);
//                posts.add(post);
//            }
//        });
        return jdbcTemplate.query(query, postRowMapper());
    }

    @Override
    public Post find(PostId id) {
        String query = "SELECT * FROM posts where id = ?";

//        jdbcTemplate.query(query, resultSet -> {
//            String title = resultSet.getString("title");
//            String author = resultSet.getString("author");
//            String content = resultSet.getString("content");
//
//            post.setTitle(title);
//            post = new Post(id, title, author, content);
//        }, id);
        return jdbcTemplate.queryForObject(query, postRowMapper(), id.toString());
    }

    @Override
    public void save(Post post) {
        String id = post.getId().toString();
        String title = post.getTitle();
        String author = post.getAuthor();
        String content = post.getContent();

        transactionTemplate.execute(status -> {
            String sql = """
                    INSERT INTO posts(id, title, author, content) VALUES(?, ?, ?, ?)
                    """;
            jdbcTemplate.update(sql, id, title, author, content);
            return null;
        });
    }

    @Override
    public void update(Post post) {
        String id = post.getId().toString();
        String title = post.getTitle();
        String content = post.getContent();

        transactionTemplate.execute(status -> {
            String sql = """
                    UPDATE posts set title = ?, content = ? WHERE id = ?
                    """;
            jdbcTemplate.update(sql, title, content, id);
            return null;
        });
    }

    @Override
    public void delete(PostId id) {
        transactionTemplate.execute(status -> {
            String sql = """
                    DELETE FROM posts WHERE id = ?
                    """;
            jdbcTemplate.update(sql, id.toString());
            return null;
        });
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) ->
            new Post(PostId.of(rs.getString("id")), rs.getString("title"),
                    rs.getString("author"), rs.getString("content"));
    }

}
