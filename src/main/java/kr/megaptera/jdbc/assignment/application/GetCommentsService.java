package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
  private final CommentRepository commentRepository;

  public GetCommentsService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;


  }

  public List<CommentResponseDto> getComments(String postId) {
    List<CommentEntity> comments = commentRepository.findAll(postId);

    return comments.stream().map(CommentResponseDto::of).toList();
  }
}
