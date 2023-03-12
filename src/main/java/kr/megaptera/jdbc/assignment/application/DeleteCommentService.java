package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
  private final CommentRepository commentRepository;

  public DeleteCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public CommentResponseDto deleteComment(String id, String postId) {
    CommentEntity comment = commentRepository
      .find(id, postId);

    commentRepository.delete(comment.getId());

    return CommentResponseDto.of(comment);
  }
}
