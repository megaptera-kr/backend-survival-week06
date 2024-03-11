package kr.megaptera.jdbc.assignment.repository;

import kr.megaptera.jdbc.assignment.daos.PostDAO;
import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.model.PostId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    private final PostDAO postDAO;

    public PostRepository(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> getPosts() {
        return postDAO.findAllPosts();
    }

    public Post getPost(PostId id) {
        return postDAO.findPostById(id);
    }

    public void savePost(Post post) {
        postDAO.savePost(post);
    }

    public void updatePost(Post post) {
        postDAO.updatePost(post);
    }

    public void deletePost(PostId postId) {
        postDAO.deletePost(postId);
    }
}
