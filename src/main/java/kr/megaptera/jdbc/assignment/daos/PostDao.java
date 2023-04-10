package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.post.*;

import java.util.*;

public interface PostDao {
    List<Post> findAll();

    Post find(String id);

    void save(Post post);

    void delete(PostId postId);
}
