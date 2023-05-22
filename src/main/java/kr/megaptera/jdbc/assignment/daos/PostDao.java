package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.*;

import java.util.*;

public interface PostDao {
    List<Post> findAll();

    Post find(PostId id);

    void save(Post post);

    void delete(PostId id);
}
