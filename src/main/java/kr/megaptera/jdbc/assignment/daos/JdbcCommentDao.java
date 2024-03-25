package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCommentDao implements CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private CommentDto transform(ResultSet rs) throws SQLException {
        return new CommentDto(rs.getString("postId")
                , rs.getString("id")
                , rs.getString("author")
                , rs.getString("content"));
    }

    @Override
    public List<CommentDto> getList(CommentDto commentDto) {
        List<CommentDto> comments = new ArrayList<>();
        String sql = """
                SELECT *
                  FROM COMMENTS
                 WHERE POST_ID = ?
                """;
        jdbcTemplate.query(sql, rs -> {
            while (rs.next()) {
                comments.add(transform(rs));
            }
        }, commentDto.getId());
        return comments;
    }

    @Override
    public void insertComment(CommentDto commentDto) {
        String sql = """
                INSERT INTO COMMENTS (POST_ID, ID, AUTHOR, CONTENT)
                              VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql
                , commentDto.getPostId()
                , commentDto.getId()
                , commentDto.getAuthor()
                , commentDto.getContent());
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        String sql = """
                UPDATE COMMENTS
                   SET CONTENT = ?
                 WHERE ID = ?
                """;
        jdbcTemplate.update(sql, commentDto.getContent(), commentDto.getId());
    }

    @Override
    public void deleteComment(CommentDto commentDto) {
        String sql = """
                DELETE FROM COMMENTS
                 WHERE ID = ?
                """;
        jdbcTemplate.update(sql, commentDto.getId());
    }
}
