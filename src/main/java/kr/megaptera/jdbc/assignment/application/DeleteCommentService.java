package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
  private final CommentDao commentDao;

  public DeleteCommentService(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  public CommentResponseDto deleteComment(String id, String postId) {
    CommentEntity comment = commentDao
      .find(id, postId);

    commentDao.delete(comment.getId());

    return CommentResponseDto.of(comment);
  }
}
