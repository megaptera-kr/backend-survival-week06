package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateRequestDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.daos.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
  private final CommentDao commentDao;

  public UpdateCommentService(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  public CommentResponseDto updateComment(String id, String postId,
                                          CommentUpdateRequestDto commentUpdatedDto) {
    CommentEntity comment = commentDao
      .find(id, postId);

    comment.update(commentUpdatedDto.getContent());

    return CommentResponseDto.of(comment);
  }
}
