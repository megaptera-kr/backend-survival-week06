package kr.megaptera.jdbc.assignment.repositories;

import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        String query = "SELECT * FROM posts";

        jdbcTemplate.query(query, resultSet -> {
            while(resultSet.next()) {
                Post post = new Post(
                       resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("content")
                );
                posts.add(post);
            }

            return null;
        });

        return posts;
    }

    public Post find(PostId id) {
        String query = "select * from posts where id = ?";
        Post post = jdbcTemplate.query(query, resultSet -> {
            if (resultSet.next()) {

                Post selectPost = new Post(
                       resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("content")
                );

                return selectPost;
            } else {
                throw new PostNotFound();
            }
        }, id.toString());
        return post;
    }

    public void save(Post post) {
        String query = "insert into posts(id, title, author, content) values (?,?,?,?)";
        jdbcTemplate.update(query, post.id().toString(), post.title(), post.author(), post.content().toString());
    }

    public void delete(PostId id) {
        String query = "delete from posts where id = ?";
        jdbcTemplate.update(query, id.toString());
    }


    public void update(Post post) {
        String query = "update posts set title=?, author=?, content=? where id=?";
        jdbcTemplate.update(query,  post.title(), post.author(), post.content().toString(), post.id().toString());
    }
}
