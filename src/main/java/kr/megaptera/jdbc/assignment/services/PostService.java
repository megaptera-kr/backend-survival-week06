package kr.megaptera.jdbc.assignment.services;

import java.util.List;
import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private JdbcPostDao jdbcPostDao;

  public PostService(JdbcPostDao jdbcPostDao) {
    this.jdbcPostDao = jdbcPostDao;
  }

  // 전체 게시글 조회
  public List<PostDto> getPostList() {
    return jdbcPostDao.getAll().stream().map(post -> post.toDto()).toList();
  }

  // 게시글 저장
  public void savePost(PostCreateDto postCreateDto) {
    Post savePost = new Post(
      postCreateDto.getTitle(),
      postCreateDto.getAuthor(),
      postCreateDto.getContent()
    );
    jdbcPostDao.save(savePost);
  }

  // 게시글 상세 조회
  public PostDto getPostDetail(String postId) {
    return jdbcPostDao.getById(PostId.of(postId)).toDto();
  }

  // 게시글 업데이트
  public PostDto updatePost(String postId, PostUpdateDto postUpdateDto) {
    Post updateTargetPost = jdbcPostDao.getById(PostId.of(postId));

    updateTargetPost.update(
      postUpdateDto.getTitle(),
      postUpdateDto.getContent()
    );
    jdbcPostDao.update(updateTargetPost);

    return updateTargetPost.toDto();
  }

  // 게시글 삭제
  public PostDto deletePost(String postId) {
    Post deleteTargetPost = jdbcPostDao.getById(PostId.of(postId));

    jdbcPostDao.delete(deleteTargetPost);

    return deleteTargetPost.toDto();
  }
}
