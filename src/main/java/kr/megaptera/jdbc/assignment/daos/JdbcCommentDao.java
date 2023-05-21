package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.domains.model.Comment;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
        List<CommentDto> comments = new ArrayList<>();
        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");

                CommentDto commentDto = new CommentDto(id, author, content);

                comments.add(commentDto);
            }
        }, postId);
        return comments;
    }

    @Override
    public CommentDto find(String id, String postId) {
        String query = "SELECT * FROM comments WHERE id = ? AND post_id = ?";
        return jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next())
                throw new CommentNotFound();
            String author = resultSet.getString("author");
            String content = resultSet.getString("content");

            return new CommentDto(id, author, content);
        }, id, postId);
    }

    @Override
    public void save(String postId, Comment comment) {
        String query = "INSERT INTO comments(id, post_id, author, content)" +
                " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(query,
                comment.id().getId(), postId, comment.author().getAuthor(), comment.content().getContent());
    }

    @Override
    public void update(String id, String postId, Comment comment) {
        String query = "UPDATE comments" +
                " SET content = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(query, comment.content().getContent(), id);
    }

    @Override
    public void delete(String id, String postId) {
        String query = "DELETE FROM comments" +
                " WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
