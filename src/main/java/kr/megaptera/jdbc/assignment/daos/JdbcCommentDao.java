package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcCommentDao implements CommentDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> findAll(PostId postId) {

        List<Comment> comments = new ArrayList<>();

        String query = "SELECT * FROM COMMENTS WHERE POST_ID = ?";

        jdbcTemplate.query(query, resultSet ->{
            while (resultSet.next()){
                Comment comment = mekeComment(resultSet);

                comments.add(comment);
            }
            return null;
        }, postId.toString());

        return comments;
    }

    @Override
    public Comment find(CommentId commentId, PostId postId) {
        List<Comment> comments = new ArrayList<>();

        String query = "SELECT * FROM COMMENTS WHERE ID = ? AND POST_ID = ?";

        Comment result = jdbcTemplate.query(query, resultSet ->{
            while (!resultSet.next())
                throw new CommentNotFound();

                Comment comment = mekeComment(resultSet);

                return comment;
        }, commentId.toString(), postId.toString());

        return result;
    }

    @Override
    public void save(Comment comment) {

        String query = """ 
                       INSERT INTO COMMENTS(ID, POST_ID, AUTHOR, CONTENT) 
                       VALUES(?,?,?,?) 
                       """;

        jdbcTemplate.update(query,
                CommentId.generate().toString(),
                comment.postId().toString(),
                comment.author(),
                comment.content()
                );
    }

    @Override
    public void update(Comment comment) {

        String query = "UPDATE COMMENTS SET CONTENT=? WHERE ID=?";

        jdbcTemplate.update(query,
                comment.content(),
                comment.id().toString()
        );

    }

    @Override
    public void delete(CommentId commentId) {

        String query = "DELETE FROM COMMENTS WHERE ID=?";

        jdbcTemplate.update(query,
                commentId.toString()
        );

    }

    private Comment mekeComment(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("ID");
        String postId = resultSet.getString("POST_ID");
        String author = resultSet.getString("AUTHOR");
        String content = resultSet.getString("CONTENT");

        Comment comment = new Comment(
                CommentId.of(id),
                PostId.of(postId),
                author,
                content);

        return comment;
    }
}
