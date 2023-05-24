package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostContent;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class JdbcPostDao implements PostDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TransactionTemplate transactionTemplate;

    @Override
    public List<Post> findAll() {
        String query = "SELECT * FROM posts";
        List<Post> posts = jdbcTemplate.query(query, (resultSet, num) -> {
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");
            Post post = new Post(PostId.of(id), PostTitle.of(title), author, PostContent.of(content));
            return post;
        });
        return posts;
    }

    @Override
    public Post findOne(String id) {
        String query = "SELECT * FROM posts WHERE id = ?";
        Post post;
        return jdbcTemplate.queryForObject(query, (rs, num) -> {
            String postId = rs.getString("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String content = rs.getString("content");

            return new Post(PostId.of(postId), PostTitle.of(title), author, PostContent.of(content));
        }, id);
    }

    @Override
    public void save(Post post) {
        transactionTemplate.execute(status -> {
            String query = "insert into posts values(?, ?, ?, ?)";
            jdbcTemplate.update(query, post.id().toString(), post.title().toString(), post.author(), post.content().toString());
            return status;
        });
    }

    @Override
    public void update(String id, Post post) {
        transactionTemplate.execute(status -> {
            String query = "update posts set title = ?, content = ? where id = ?";
            jdbcTemplate.update(query, post.title().toString(), post.content().toString(), id);
            return status;
        });
    }

    @Override
    public void delete(String id) {
        transactionTemplate.execute(status -> {
            String query = "delete from posts where id = ?";
            jdbcTemplate.update(query, id);
            return status;
        });

    }
}
