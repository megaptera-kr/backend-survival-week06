package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
  private final PostRepository postRepository;

  public DeletePostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponseDto deletePost(String id) {
    PostEntity post = postRepository.find(id);

    postRepository.delete(id);

    return PostResponseDto.of(post);
  }
}
