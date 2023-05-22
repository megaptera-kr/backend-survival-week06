package kr.megaptera.jdbc.assignment.repositories;

import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> findAll(PostId postId) {
        List<Comment> comments = new ArrayList<>();

        String query = "SELECT * FROM comments";

        jdbcTemplate.query(query, resultSet -> {
            while(resultSet.next()) {
                Comment comment = new Comment(
                        CommentId.of(resultSet.getString("id")),
                        PostId.of(resultSet.getString("post_id")),
                        resultSet.getString("author"),
                        resultSet.getString("content")
                );
                comments.add(comment);
            }

            return null;
        });

        return comments;
    }

    public Comment find(CommentId id, PostId postId) {
        //Comment comment = comments.get(id);
        String query = "SELECT * FROM comments where id = ?";

        Comment result = jdbcTemplate.query(query, resultSet -> {
            if (resultSet.next()) {
                Comment comment = new Comment(
                        CommentId.of(resultSet.getString("id")),
                        PostId.of(resultSet.getString("post_id")),
                        resultSet.getString("author"),
                        resultSet.getString("content")
                );
                return comment;
            }else {
                throw new CommentNotFound();
            }
        }, id.toString());

        return result;
    }

    public void save(Comment comment) {
        //comments.put(comment.id(), comment);
        String query = "insert into comments(id, post_id, author, content) values (?,?,?,?)";
        jdbcTemplate.update(query, comment.id().toString(), comment.postId().toString() , comment.author(), comment.content().toString());

    }

    public void delete(CommentId commentId) {
        //comments.remove(commentId);
        String query = "delete from comments where id = ?";
        jdbcTemplate.update(query, commentId.toString());
    }

    public void update(String id, String content) {
        String query = "update comment " +
                "set content = ? where id = ?";
        jdbcTemplate.update(query,content, id);
    }
}
