package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.utils.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcCommentDao implements CommentDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> findAll(String postId) {
        List<Comment> comments = new ArrayList<>();

        String sql = "SELECT * FROM comments where post_id = ?";

        jdbcTemplate.query(sql, resultSet -> {
            while (resultSet.next()) {
                Comment comment = Comment.builder()
                        .id(resultSet.getString("id"))
                        .postId(resultSet.getString("post_id"))
                        .author(resultSet.getString("author"))
                        .content(resultSet.getString("content"))
                        .build();
                comments.add(comment);
            }
            return null;
        }, postId);
        return comments;
    }

    @Override
    public Comment find(String id, String postId) {
        String sql = "SELECT * FROM comments WHERE id = ? AND post_id = ?";

        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> Comment.builder()
                .id(resultSet.getString("id"))
                .postId(resultSet.getString("post_id"))
                .author(resultSet.getString("author"))
                .content(resultSet.getString("content"))
                .build(), id, postId);

    }

    @Override
    public void createComment(Comment comment) {
        String sql = "INSERT INTO comments (id, post_id, author, content) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, PostUtil.getId(), comment.getPostId(), comment.getAuthor(), comment.getContent());
    }

    @Override
    public void updateComment(Comment comment) {
        String sql = "UPDATE comments SET content = ? WHERE id = ?";
        jdbcTemplate.update(sql, comment.getContent(), comment.getId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
