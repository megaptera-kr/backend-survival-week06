package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Component
public class JdbcPostDao implements PostDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        String query = "SELECT * FROM posts";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                Post post = extractPost(resultSet);
                posts.add(post);
            }

            return null;
        });

        return posts;
    }

    @Override
    public Post find(PostId id) {
        String query = "SELECT * FROM posts WHERE id=?";

        Post found = jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next()) {
                try {
                    throw new PostNotFound();
                } catch (PostNotFound e) {
                    throw new RuntimeException(e);
                }
            }

            Post post = extractPost(resultSet);

            return post;
        }, id.toString());

        return found;
    }

    @Override
    public void save(Post post) {
        if (post.id() != null) {
            System.out.println("here!!");
            updatePost(post);
            return;
        }
        System.out.println("there!!");
        insertPost(post);
    }

    @Override
    public void delete(PostId id) {
        // Hard delete 방식으로 데이터 자체를 삭제.
        String query = "DELETE FROM posts WHERE id=?";

        jdbcTemplate.update(query, id.toString());

        // 또는 Soft delete 방식으로 따로 "isDeleted" 속성 등을 만들어 삭제처리를 해주기도 합니다.
    }

    private Post extractPost(ResultSet resultSet) throws SQLException {
        String postId = resultSet.getString("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");

        Post post = new Post(
                PostId.of(postId),
                title, author,
                new MultilineText(content)
        );

        return post;
    }

    private void insertPost(Post post) {
        System.out.println("jerer");
        String query = """
            INSERT INTO posts (id, title, author, content) VALUES (?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                query,
                PostId.generate().toString(),
                post.title(),
                post.author(),
                post.content().toString()
        );
    }

    private void updatePost(Post post) {
        String query = """
            UPDATE posts SET title=?, content=? WHERE id=?
            """;

        jdbcTemplate.update(
                query,
                post.title(),
                post.content().toString(),
                post.id().toString()
        );
    }
}
