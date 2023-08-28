package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.CommnetNotFound;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
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
    public List<Comment> findAll(PostId postId) {
        List<Comment> commnets = new ArrayList<>();

        String query = "SELECT * FROM comments WHERE post_id = ?";

        jdbcTemplate.query(query, resultSet -> {
            while (resultSet.next()) {
                Comment comment = extractComment(resultSet);
                commnets.add(comment);
            }

            return null;
        }, postId.toString());

        return commnets;
    }


    @Override
    public Comment find(CommentId id, PostId postId) {
        String query = "SELECT * FROM comments WHERE id=? AND post_id = ?";

        Comment found = jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next()) {
                throw new CommnetNotFound();
            }

            Comment comment = extractComment(resultSet);

            return comment;
        }, id.toString(), postId.toString());

        return found;
    }

    @Override
    public void save(Comment comment) {
        if (comment.id() != null) {
            updateComment(comment);
            return;
        }

        insertComment(comment);

    }


    @Override
    public void delete(CommentId id) {
        // Hard delete 방식으로 데이터 자체를 삭제.
        String query = "DELETE FROM comments WHERE id=?";

        jdbcTemplate.update(query, id.toString());

        // 또는 Soft delete 방식으로 따로 "isDeleted" 속성 등을 만들어 삭제처리를 해주기도 합니다.
    }


    private Comment extractComment(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String postId = resultSet.getString("post_id");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");

        Comment comment = new Comment(
                CommentId.of(id),
                PostId.of(postId),
                author,
                content
        );

        return comment;
    }


    private void insertComment(Comment comment) {
        String query = """
                INSERT INTO comments values (?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                query,
                CommentId.generate().toString(),
                comment.postId().toString(),
                comment.author(),
                comment.content()
        );
    }


    private void updateComment(Comment comment) {
        String query = """
                UPDATE comments SET content=? WHERE id=?
                """;

        jdbcTemplate.update(
                query,
                comment.content(),
                comment.id().toString()
        );
    }

}
