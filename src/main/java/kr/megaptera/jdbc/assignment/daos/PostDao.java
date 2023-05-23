package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    List<Post> findAll();

    Post findById(PostId id);

    Post savePost(Post post);

    void deletePost(Post post);

    Post updatePost(Post post);
}
