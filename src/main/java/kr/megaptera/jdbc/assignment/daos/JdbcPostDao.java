package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostAuthor;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao {
    private final JdbcTemplate jdbcTemplate;
    private TransactionTemplate transactionTemplate;

    @Autowired
    public JdbcPostDao(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM post";
        jdbcTemplate.query(query, resultSet -> {
            do {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                Post post = new Post(PostId.of(id), PostTitle.of(title), PostAuthor.of(author), MultilineText.of(content));
                posts.add(post);
            } while (resultSet.next());
        });
        return posts;
    }

    @Override
    public Post find(PostId id) {
        String query = "SELECT * FROM post WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
            int postId = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");
            return new Post(PostId.of(postId), PostTitle.of(title), PostAuthor.of(author), MultilineText.of(content));
        }, id.toInt());
    }

    @Override
    public void save(Post post) {
        transactionTemplate.execute(status -> {
            String query = "INSERT INTO post (title, author, content) VALUES ( ?, ?, ?)";
            jdbcTemplate.update(query,
                    post.title().toString(),
                    post.author().toString(),
                    post.content().toString());
            return null;
        });
    }

    @Override
    public void delete(PostId id) {
        transactionTemplate.execute(status -> {
            String query = "DELETE FROM post WHERE id = ?";
            jdbcTemplate.update(query, id.toInt());
            return null;
        });
    }

    @Override
    public void clear() {
        transactionTemplate.execute(status -> {
            String query = "DELETE FROM post";
            jdbcTemplate.update(query);
            return null;
        });
    }

    @Override
    public void update(Post post) {
        transactionTemplate.execute(status -> {
            int updatedRows = jdbcTemplate.update(
                    "UPDATE post SET title = ?, content = ? WHERE id = ?",
                    post.title().toString(),
                    post.content().toString(),
                    post.id().toInt()
            );

            if (updatedRows == 0) {
                throw new PostNotFound();
            }
            return null;
        });
    }

}
