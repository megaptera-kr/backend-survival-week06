package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostCreateDto postCreateDto) {
        Post post = new Post(
            postCreateDto.getTitle(),
            postCreateDto.getAuthor(),
            MultilineText.of(postCreateDto.getContent())
        );

        postRepository.save(post);

        return new PostDto(post);
    }
}
