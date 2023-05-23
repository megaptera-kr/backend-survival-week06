package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.*;
import kr.megaptera.jdbc.assignment.dtos.*;

import java.util.*;


public interface PostDao {
    List<Post> findAll();

    Post find(String id);

    void delete(String id);

    void create(Post post);

    void update(String id, PostUpdateDto postUpdateDto);
}
