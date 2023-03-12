package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.entities.PostEntity;

import java.util.List;

public interface PostDao {
  List<PostEntity> findAll();

  PostEntity find(String id);
  void save(PostEntity post);

  void delete(String id);
}
