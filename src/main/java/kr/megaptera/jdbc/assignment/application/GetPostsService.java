package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.daos.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
  private final PostDao postDao;

  public GetPostsService(PostDao postDao) {
    this.postDao = postDao;
  }

  public List<PostResponseDto> getPosts() {
    List<PostEntity> posts = postDao.findAll();

    return posts.stream().map(PostResponseDto::of).toList();
  }
}