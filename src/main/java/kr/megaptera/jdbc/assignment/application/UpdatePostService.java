package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateRequestDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.daos.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
  private final PostDao postDao;

  public UpdatePostService(PostDao postDao) {
    this.postDao = postDao;
  }

  public PostResponseDto updatePost(String id, PostUpdateRequestDto postUpdateDto) {
    PostEntity post = postDao.find(id);

    post.update(
      postUpdateDto.getTitle(),
      postUpdateDto.getContent()
    );

    return PostResponseDto.of(post);
  }
}
