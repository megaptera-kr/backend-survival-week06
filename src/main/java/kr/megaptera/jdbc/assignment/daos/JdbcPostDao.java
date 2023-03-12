package kr.megaptera.jdbc.assignment.daos;

import com.github.f4b6a3.tsid.TsidCreator;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        String query = "SELECT * FROM testpost";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                Post post = new Post(
                        PostId.of(id),
                        title,
                        author,
                        MultilineText.of(content));

                posts.add(post);
            }
        });

        return posts;
    }

    @Override
    public Post find(PostId id) {

        String query = "SELECT * FROM testpost WHERE id = ?";

        Post post = jdbcTemplate.query(query, resultSet -> {
                String resId = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");

                return new Post(
                        PostId.of(resId),
                        title,
                        author,
                        MultilineText.of(content));

        }, id.toString());

        return post;
    }

    @Override
    public void save(Post post) {

        transactionTemplate.execute(status -> {
            String sql = "INSERT INTO testpost(id, title, author, content) VALUES(?, ?, ?, ?) ";

            jdbcTemplate.update(
                    sql,
                    post.id(),
                    post.title(),
                    post.author(),
                    post.content().toString()
            );

            return null;
        });

    }

    @Override
    public void delete(PostId id) {
        transactionTemplate.execute(status -> {
            String sql = "DELETE FROM testpost WHERE id = ?";

            jdbcTemplate.update(
                    sql,
                    id.toString()
            );

            return null;
        });
    }
}
