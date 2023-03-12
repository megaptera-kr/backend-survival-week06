package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentCreateRequestDto;
import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {
  private CommentRepository commentRepository;

  private CreateCommentService createCommentService;

  @BeforeEach
  void setUp() {
    commentRepository = mock(CommentRepository.class);

    createCommentService = new CreateCommentService(commentRepository);
  }

  @Test
  @DisplayName("댓글 생성")
  void create() {
    String postId = "001POST";

    CommentCreateRequestDto newComment =
      new CommentCreateRequestDto("작성자", "댓글 내용");

    CommentResponseDto commentDto =
      createCommentService.createComment(postId, newComment);

    assertThat(commentDto.getAuthor()).isEqualTo("작성자");
    assertThat(commentDto.getContent()).isEqualTo("댓글 내용");

    verify(commentRepository).save(any(CommentEntity.class));
  }
}
