package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.entities.CommentEntity;

import java.util.List;

public interface CommentDao {
  List<CommentEntity> findAll(String postId);
  CommentEntity find(String id, String postId);
  void save(CommentEntity comment);
  void delete(String commentId);
}
