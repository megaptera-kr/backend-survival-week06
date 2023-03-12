package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.entities.CommentEntity;

import java.util.List;

public class JdbcCommentDao implements CommentDao {
  @Override
  public List<CommentEntity> findAll(String postId) {
    return null;
  }

  @Override
  public CommentEntity find(String id, String postId) {
    return null;
  }

  @Override
  public void save(CommentEntity comment) {

  }

  @Override
  public void delete(String commentId) {

  }
}
