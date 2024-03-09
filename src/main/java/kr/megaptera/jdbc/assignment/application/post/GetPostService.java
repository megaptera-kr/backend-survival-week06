package kr.megaptera.jdbc.assignment.application.post;

import kr.megaptera.jdbc.assignment.dtos.post.GetPostDTO;
import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.model.PostId;
import kr.megaptera.jdbc.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {

    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public GetPostDTO getPost(String id) {
        PostId postId = PostId.of(id);
        Post post = postRepository.getPost(postId);
        return GetPostDTO.of(post);
    }
}
