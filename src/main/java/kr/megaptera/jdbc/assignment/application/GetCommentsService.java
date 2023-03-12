package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.daos.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
  private final CommentDao commentDao;

  public GetCommentsService(CommentDao commentDao) {
    this.commentDao = commentDao;


  }

  public List<CommentResponseDto> getComments(String postId) {
    List<CommentEntity> comments = commentDao.findAll(postId);

    return comments.stream().map(CommentResponseDto::of).toList();
  }
}
