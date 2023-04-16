package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFoundException;

import java.util.List;

public interface PostDao {
    List<PostEntity> findAll();
    PostEntity find(String id) throws PostNotFoundException;
    void add(PostEntity post);
    void update(String id, PostEntity post);
    void remove(String id);
}
