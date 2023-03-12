package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateRequestDto;
import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
  private final CommentDao commentDao;

  public CreateCommentService(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  public CommentResponseDto createComment(String postId,
                                          CommentCreateRequestDto commentCreateDto) {
    CommentEntity comment = new CommentEntity(
      postId,
      commentCreateDto.getAuthor(),
      commentCreateDto.getContent()
    );

    commentDao.save(comment);

    return CommentResponseDto.of(comment);
  }
}
