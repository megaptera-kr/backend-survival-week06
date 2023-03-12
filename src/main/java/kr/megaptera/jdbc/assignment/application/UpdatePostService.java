package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateRequestDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
  private final PostRepository postRepository;

  public UpdatePostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponseDto updatePost(String id, PostUpdateRequestDto postUpdateDto) {
    PostEntity post = postRepository.find(id);

    post.update(
      postUpdateDto.getTitle(),
      postUpdateDto.getContent()
    );

    return PostResponseDto.of(post);
  }
}
