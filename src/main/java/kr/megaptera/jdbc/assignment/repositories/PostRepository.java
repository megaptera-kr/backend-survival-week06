package kr.megaptera.jdbc.assignment.repositories;

import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        String query = "select * from posts";
        jdbcTemplate.query(query, resultSet -> {
            while(resultSet.next()) {
                System.out.println(123);
                Post post = new Post(
                        new PostId(resultSet.getString("id")),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        new MultilineText(resultSet.getString("content"))
                );
                posts.add(post);
            }
        });
        return posts;
    }

    public Post find(PostId id) {
        String query = "select * from posts where id = ?";
        jdbcTemplate.query(query, resultSet -> {
            if(resultSet.next()) {

                Post post = new Post(
                        new PostId(resultSet.getString("id")),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        new MultilineText(resultSet.getString("content"))
                );

                return post;
            }else{
                throw new PostNotFound();
            }
        });
        throw new PostNotFound();
    }

    public void save(Post post) {
        String query = "insert into posts(id, title, author, content) values (?,?,?,?)";
        jdbcTemplate.update(query, post.id().toString(), post.title(), post.author(), post.content().toString());
    }

    public void delete(PostId id) {
        String query = "delete from posts where id = '?'";
        jdbcTemplate.update(query, id.toString());
    }
}
