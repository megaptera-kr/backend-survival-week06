package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.domains.model.Comment;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
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

    @Override
    public List<CommentDto> findByPost(String postId) {
        String query = "SELECT * FROM comments WHERE post_id = ?";
        return jdbcTemplate.query(query, resultSet -> {
            List<CommentDto> newComments = new ArrayList<>();
            while (resultSet.next()) {
                CommentDto commentDto = getDataFromRow(resultSet);
                newComments.add(commentDto);
            }
            return newComments;
        }, postId);
    }

    @Override
    public CommentDto find(String id, String postId) {
        String query = "SELECT * FROM comments WHERE id = ? AND post_id = ?";
        return jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next())
                throw new CommentNotFound();

            return getDataFromRow(resultSet);
        }, id, postId);
    }

    @Override
    public void save(String postId, Comment comment) {
        String query = "INSERT INTO comments(id, post_id, author, content)" +
                " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(query,
                comment.id().getId(), postId, comment.author().getAuthor(), comment.content().toString());
    }

    @Override
    public void update(String id, String postId, Comment comment) {
        String query = "UPDATE comments" +
                " SET content = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(query, comment.content().toString(), id);
    }

    @Override
    public void delete(String id, String postId) {
        String query = "DELETE FROM comments" +
                " WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    private CommentDto getDataFromRow(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");
        return new CommentDto(id, author, content);
    }
}
