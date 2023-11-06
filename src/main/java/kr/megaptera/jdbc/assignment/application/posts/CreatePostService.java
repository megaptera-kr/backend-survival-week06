package kr.megaptera.jdbc.assignment.application.posts;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.jdbc.assignment.dtos.posts.PostDto;
import kr.megaptera.jdbc.assignment.models.posts.Post;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    private final PostDao postRepository;

    public CreatePostService(PostDao postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto create(CreatePostDto createPostDto) {
        Post newPost = new Post(
                createPostDto.getTitle(),
                createPostDto.getAuthor(),
                createPostDto.getContent()
        );
        postRepository.save(newPost);

        return PostDto.of(newPost);
    }
}
