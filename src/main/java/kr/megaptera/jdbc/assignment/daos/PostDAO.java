package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.model.PostId;

import java.util.List;

public interface PostDAO {

    List<Post> findAllPosts();

    Post findPostById(PostId id);

    void savePost(Post post);

    void updatePost(Post post);

    void deletePost(PostId postId);
}
