package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.posts.Post;
import kr.megaptera.jdbc.assignment.models.posts.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao {
    private final JdbcTemplate jdbcTemplate;

    private final String POST_TABLE_NAME = "post";

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = """
                INSERT INTO %s (title, author, content) VALUES (?, ?, ?);
                """.formatted(POST_TABLE_NAME);

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getAuthor());
            ps.setString(3, post.getContent());

            return ps;
        }, keyHolder);

        // db에서 생성된 id를 postId에 저장
        post.setId(new PostId(keyHolder.getKeys().get("id").toString()));
    }

    public List<Post> findAll() {
        List<Post> listPost = new ArrayList<>();

        String sql = """
                SELECT *
                FROM %s;
                """.formatted(POST_TABLE_NAME);

        jdbcTemplate.query(sql, rs -> {
            listPost.add(new Post(
                    new PostId(rs.getString("id")),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("content")
            ));
        });

        return listPost;
    }
}
