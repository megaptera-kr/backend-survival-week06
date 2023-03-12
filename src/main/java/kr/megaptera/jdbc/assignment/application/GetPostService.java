package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.daos.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
  private final PostDao postDao;

  public GetPostService(PostDao postDao) {
    this.postDao = postDao;
  }

  public PostResponseDto getPostDetail(String id) {
    PostEntity post = postDao.find(id);

    return PostResponseDto.of(post);
  }
}