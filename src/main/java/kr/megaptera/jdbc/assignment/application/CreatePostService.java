package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostCreateRequestDto;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
  private final PostRepository postRepository;

  public CreatePostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponseDto createPost(PostCreateRequestDto postCreateDto) {
    PostEntity post = new PostEntity(
      postCreateDto.getTitle(),
      postCreateDto.getAuthor(),
      postCreateDto.getContent()
    );

    postRepository.save(post);

    return PostResponseDto.of(post);
  }
}
