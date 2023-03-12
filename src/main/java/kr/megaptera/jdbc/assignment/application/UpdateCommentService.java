package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateRequestDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
  private final CommentRepository commentRepository;

  public UpdateCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public CommentResponseDto updateComment(String id, String postId,
                                          CommentUpdateRequestDto commentUpdatedDto) {
    CommentEntity comment = commentRepository
      .find(id, postId);

    comment.update(commentUpdatedDto.getContent());

    return CommentResponseDto.of(comment);
  }
}
