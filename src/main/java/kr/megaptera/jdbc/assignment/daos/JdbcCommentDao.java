package kr.megaptera.jdbc.assignment.daos;

import java.util.ArrayList;
import java.util.List;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCommentDao implements CommentDao {

  private JdbcTemplate jdbcTemplate;

  public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Comment> getCommentList(PostId postId) {
    List<Comment> result = new ArrayList<>();

    String listSql =
      """
                SELECT * FROM comments WHERE postId = ?;
                """;
    jdbcTemplate.query(
      listSql,
      resultSet -> {
        while (resultSet.next()) {
          result.add(
            new Comment(
              CommentId.of(resultSet.getString("id")),
              resultSet.getString("author"),
              resultSet.getString("content")
            )
          );
        }
        return null;
      },
      postId.toString()
    );

    return result;
  }

  @Override
  public Comment findById(CommentId commentId) {
    String sql =
      """
                SELECT * FROM comments WHERE id = ?;
                """;
    return jdbcTemplate.query(
      sql,
      ResultSet -> {
        if (ResultSet.next()) {
          return new Comment(
            CommentId.of(ResultSet.getString("id")),
            ResultSet.getString("author"),
            ResultSet.getString("content")
          );
        } else {
          return null;
        }
      },
      commentId.toString()
    );
  }

  @Override
  public void save(PostId postId, Comment newComment) {
    String saveSql =
      """
                INSERT INTO comments(id, postId, author, content) values(?,?,?,?);
                """;
    jdbcTemplate.update(
      saveSql,
      newComment.id().toString(),
      postId.toString(),
      newComment.author(),
      newComment.content()
    );
  }

  @Override
  public void update(Comment comment) {
    String updateSql =
      """
                UPDATE comments SET content = ? WHERE id = ?;
                """;
    jdbcTemplate.update(updateSql, comment.content(), comment.id().toString());
  }

  @Override
  public void delete(Comment comment) {
    String deleteSql =
      """
                DELETE FROM comments WHERE id = ?;
                """;
    jdbcTemplate.update(deleteSql, comment.id().toString());
  }
}
