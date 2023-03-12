package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
  private final PostRepository postRepository;

  public GetPostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponseDto getPostDetail(String id) {
    PostEntity post = postRepository.find(id);

    return PostResponseDto.of(post);
  }
}
