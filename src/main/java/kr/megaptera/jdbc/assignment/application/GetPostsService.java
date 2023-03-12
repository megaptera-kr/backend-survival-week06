package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
  private final PostRepository postRepository;

  public GetPostsService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<PostResponseDto> getPosts() {
    List<PostEntity> posts = postRepository.findAll();

    return posts.stream().map(PostResponseDto::of).toList();
  }
}
