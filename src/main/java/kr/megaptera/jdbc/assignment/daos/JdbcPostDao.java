package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcPostDao implements PostDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        String query = "SELECT * FROM POSTS";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next())
            {
                Post post = makePost(resultSet);

                posts.add(post);
            }
            return null;
        });

        return posts;
    }

    @Override
    public Post find(PostId postId) {
        String query = "SELECT * FROM POSTS WHERE ID = ?";

        Post result = jdbcTemplate.query(query, resultSet -> {
            while (!resultSet.next())
                throw new PostNotFound();

                Post post = makePost(resultSet);

                return post;

        }, postId.toString());

        return result;
    }

    public void save(Post post) {
        String query = """
                       INSERT INTO POSTS(ID,TITLE,AUTHOR,CONTENT) 
                       VALUES(?,?,?,?)
                       """;

        jdbcTemplate.update(query,
                PostId.generate().toString(),
                post.title(),
                post.author(),
                post.content().toString());
    }

    @Override
    public void update(Post post) {
        String query = "UPDATE POSTS SET TITLE=?, CONTENT=? WHERE ID=?";

        jdbcTemplate.update(query,
                post.title(),
                post.content().toString(),
                post.id().toString());
    }

    @Override
    public void delete(PostId postId) {
        String query = "DELETE FROM POSTS WHERE ID=?";

        jdbcTemplate.update(query,
                postId.toString());
    }

    public Post makePost(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("ID");
        String title = resultSet.getString("TITLE");
        String author = resultSet.getString("AUTHOR");
        String content = resultSet.getString("CONTENT");

        Post post = new Post(
                PostId.of(id),
                title,
                author,
                MultilineText.of(content));
        return post;
    }

}
