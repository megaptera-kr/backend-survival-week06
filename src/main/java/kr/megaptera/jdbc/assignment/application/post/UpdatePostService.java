package kr.megaptera.jdbc.assignment.application.post;

import kr.megaptera.jdbc.assignment.dtos.post.UpdatePostDTO;
import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.model.PostId;
import kr.megaptera.jdbc.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void updatePost(String id, UpdatePostDTO updatePostDTO) {
        PostId postId = PostId.of(id);
        Post foundPost = this.postRepository.getPost(postId);
        Post updatedPost = Post.copy(foundPost, updatePostDTO.title(), updatePostDTO.content());
        this.postRepository.updatePost(updatedPost);
    }
}
