package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {
  private CommentRepository commentRepository;

  private GetCommentsService getCommentsService;

  @BeforeEach
  void setUp() {
    commentRepository = mock(CommentRepository.class);

    getCommentsService = new GetCommentsService(commentRepository);
  }

  @Test
  @DisplayName("댓글 목록 조회")
  void list() {
    String postId = "1";

    given(commentRepository.findAll(postId)).willReturn(List.of(
      new CommentEntity(
        "2",
        postId,
        "작성자",
        "댓글 내용"
      )
    ));

    List<CommentResponseDto> commentDtos
      = getCommentsService.getComments(postId);

    assertThat(commentDtos).hasSize(1);
  }
}
