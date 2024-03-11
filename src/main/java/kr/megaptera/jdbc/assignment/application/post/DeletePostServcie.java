package kr.megaptera.jdbc.assignment.application.post;

import kr.megaptera.jdbc.assignment.model.PostId;
import kr.megaptera.jdbc.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostServcie {
    private final PostRepository postRepository;

    public DeletePostServcie(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deletePost(String id) {
        PostId postId = PostId.of(id);
        postRepository.deletePost(postId);
    }
}
