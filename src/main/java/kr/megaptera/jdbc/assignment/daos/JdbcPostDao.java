package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.NotFoundException;
import kr.megaptera.jdbc.assignment.models.Author;
import kr.megaptera.jdbc.assignment.models.Content;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.Title;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcPostDao implements PostDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {
        List<Post> postList = new ArrayList<>();

        String query = "SELECT * FROM posts";

        jdbcTemplate.query(query, rs -> {
            while (rs.next()) {
                postList.add(postBuilder(rs));
            }
        });
        return null;
    }

    @Override
    public Post findById(PostId id) {
        String query = "SELECT * FROM posts WHERE id=?";

        Post post = jdbcTemplate.query(query, rs -> {
            if (!rs.next()) {
                throw new NotFoundException();
            }
            return postBuilder(rs);
        }, id.toString());

        return post;
    }

    @Override
    public Post savePost(Post post) {
        return null;
    }

    @Override
    public void deletePost(Post post) {

    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    protected Post postBuilder(ResultSet rs) throws SQLException {
        String postId = rs.getString("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        String content = rs.getString("content");

        return new Post(PostId.of(Integer.parseInt(postId)), Title.of(title),
                Author.of(author), Content.of(content));
    }
}
