package kr.megaptera.jdbc.assignment.daos;

import java.util.ArrayList;
import java.util.List;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPostDao implements PostDao {

  private JdbcTemplate jdbcTemplate;

  public JdbcPostDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // 전체 게시글 조회
  @Override
  public List<Post> getAll() {
    List<Post> result = new ArrayList<>();
    String findAllSql = """
        SELECT * FROM posts;
        """;
    jdbcTemplate.query(
      findAllSql,
      resultSet -> {
        while (resultSet.next()) {
          Post post = new Post(
            PostId.of(resultSet.getString("id")),
            resultSet.getString("title"),
            resultSet.getString("author"),
            resultSet.getString("content")
          );
          result.add(post);
        }
        return null;
      }
    );
    return result;
  }

  // 게시물 생성
  @Override
  public void save(Post post) {
    String insertSql =
      """
            INSERT INTO posts(id,title,author,content) values(?,?,?,?);
            """;
    jdbcTemplate.update(
      insertSql,
      post.id().toString(),
      post.title(),
      post.author(),
      post.content()
    );
  }

  // 게시글 상세 조회
  @Override
  public Post getById(PostId id) {
    String findSql =
      """
            SELECT * FROM posts WHERE id = ?
            """;

    return jdbcTemplate.query(
      findSql,
      resultSet -> {
        while (resultSet.next()) {
          Post post = new Post(
            PostId.of(resultSet.getString("id")),
            resultSet.getString("title"),
            resultSet.getString("author"),
            resultSet.getString("content")
          );
          return post;
        }
        return null;
      },
      id.toString()
    );
  }

  @Override
  public void update(Post post) {
    String updateSql =
      """
            UPDATE posts set title = ?, content = ? WHERE id = ?;
            """;
    jdbcTemplate.update(
      updateSql,
      post.title(),
      post.content(),
      post.id().toString()
    );
  }

  @Override
  public void delete(Post post) {
    String deleteSql =
      """
            DELETE FROM posts where id = ?;
            """;
    jdbcTemplate.update(deleteSql, post.id().toString());
  }
}
