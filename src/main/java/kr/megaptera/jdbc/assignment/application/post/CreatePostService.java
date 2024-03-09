package kr.megaptera.jdbc.assignment.application.post;

import kr.megaptera.jdbc.assignment.dtos.post.CreatePostDTO;
import kr.megaptera.jdbc.assignment.dtos.post.GetPostDTO;
import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public GetPostDTO createPost(CreatePostDTO createPostDTO) {
        Post post = new Post(createPostDTO.title(), createPostDTO.author(), createPostDTO.content());
        this.postRepository.savePost(post);
        return GetPostDTO.of(post);
    }
}
