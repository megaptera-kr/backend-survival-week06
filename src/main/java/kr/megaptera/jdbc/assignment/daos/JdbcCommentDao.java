package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcCommentDao implements CommentDao {

    private final JdbcTemplate jdbcTemplate;

    private final TransactionTemplate transactionTemplate;

    @Override
    public List<Comment> findByPostId(String postId) {
        String query = """
                SELECT * FROM comments 
                LEFT OUTER JOIN posts ON comments.postId = posts.id
                WHERE posts.id = ?
            """;
        return jdbcTemplate.query(query, commentRowMapper(), postId.toString());
    }

    @Override
    public Comment find(CommentId id) {
        String query = "SELECT * FROM comments WHERE id = ?";
        return jdbcTemplate.queryForObject(query, commentRowMapper(), id.toString());
    }

    @Override
    public void save(Comment comment) {
        String id = comment.getId().toString();
        String postId = comment.getPostId().toString();
        String author = comment.getAuthor();
        String content = comment.getContent();

        transactionTemplate.execute(status -> {
            String sql = "INSERT INTO comments(id, postId, author, content) VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(sql, id, postId, author, content);
            return null;
        });
    }

    @Override
    public void update(Comment comment) {
        String id = comment.getId().toString();
        String content = comment.getContent();

        transactionTemplate.execute(status -> {
            String sql = "UPDATE comments SET content = ? WHERE id = ?";
            jdbcTemplate.update(sql, content, id);
            return null;
        });
    }

    @Override
    public void delete(CommentId id) {
        transactionTemplate.execute(status -> {
            String sql = "DELETE FROM comments WHERE id = ?";
            jdbcTemplate.update(sql, id.toString());
            return null;
        });
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) ->
                new Comment(CommentId.of(rs.getString("id")), PostId.of(rs.getString("postId")),
                        rs.getString("author"), rs.getString("content"));
    }

}
