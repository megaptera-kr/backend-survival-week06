package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.List;

public interface PostDao {

    List<Post> findAll();

    Post find(PostId postId);

    void save(Post post);

    void update(Post post);

    void delete(PostId postId);
}
