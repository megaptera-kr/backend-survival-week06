package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface PostDao {
    List<Post> findAll();

    Post findOne(String id);

    void save(Post post);

    void update(String id, Post post);

    void delete(String id);
}
