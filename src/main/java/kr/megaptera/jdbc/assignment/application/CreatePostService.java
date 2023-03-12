package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostCreateRequestDto;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
  private final PostDao postDao;

  public CreatePostService(PostDao postDao) {
    this.postDao = postDao;
  }

  public PostResponseDto createPost(PostCreateRequestDto postCreateDto) {
    PostEntity post = new PostEntity(
      postCreateDto.getTitle(),
      postCreateDto.getAuthor(),
      postCreateDto.getContent()
    );

    postDao.save(post);

    return PostResponseDto.of(post);
  }
}
