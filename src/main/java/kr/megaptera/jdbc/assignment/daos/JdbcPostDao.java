package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcPostDao implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        jdbcTemplate.query(sql, resultSet -> {
            while (resultSet.next()) {
                Post post = Post.builder()
                        .id(resultSet.getString("id"))
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .author(resultSet.getString("author"))
                        .build();
                posts.add(post);
            }
            return null;
        });
        return posts;
    }

    @Override
    public Post find(String id) {
        String sql = "SELECT * FROM posts WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, num) -> {
            String postId = rs.getString("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String content = rs.getString("content");

            return new Post(postId, title, author, content);
        }, id);
    }

    @Override
    public void createPostSave(Post post) {
        String sql = "INSERT INTO posts (id, title, content, author) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,post.getId(), post.getTitle(), post.getContent(), post.getAuthor());
    }

    @Override
    public void updatePostsave(Post post) {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
