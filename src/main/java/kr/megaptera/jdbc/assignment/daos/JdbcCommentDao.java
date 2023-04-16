package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Component
public class JdbcCommentDao implements CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> findAll(PostId postId) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT * FROM comments WHERE postid = ?";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                Comment comment = getComment(resultSet);
                comments.add(comment);
            }
            return null;
        }, postId.toString());
        return comments;
    }

    @Override
    public void create(Comment comment) {
        String query = "INSERT INTO comments(postid, id, author, content) VALUES(?,?,?,?)";
        jdbcTemplate.update(query, comment.postId().toString(), comment.id().toString(), comment.author(), comment.content());
    }

    @Override
    public void update(Comment comment) {
        String query = "UPDATE comments SET content = ? WHERE id =?";
        jdbcTemplate.update(query, comment.content(), comment.id().toString());
    }

    @Override
    public Comment find(CommentId commentId, PostId postId) {
        String query = "SELECT * FROM comments WHERE id = ? AND postid = ?";
        Comment comment = jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next()) {
                throw new CommentNotFound();
            }

            Comment _comment = getComment(resultSet);
            return _comment;
        }, commentId.toString(), postId.toString());

        return comment;
    }

    @Override
    public void delete(String postId, String id) {
        String query = "DELETE FROM comments WHERE id =? AND postid=?";
        jdbcTemplate.update(query, id, postId);
    }

    private Comment getComment(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");
        String _postId = resultSet.getString("postid");
        Comment comment = new Comment(
                CommentId.of(id),
                author,
                content,
                PostId.of(_postId)
        );
        return comment;
    }
}
