package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
  private final PostDao postDao;

  public DeletePostService(PostDao postDao) {
    this.postDao = postDao;
  }

  public PostResponseDto deletePost(String id) {
    PostEntity post = postDao.find(id);

    postDao.delete(id);

    return PostResponseDto.of(post);
  }
}
