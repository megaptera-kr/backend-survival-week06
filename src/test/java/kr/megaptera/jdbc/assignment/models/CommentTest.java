package kr.megaptera.jdbc.assignment.models;

import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {
  @Test
  void creation() {

    CommentEntity comment = new CommentEntity(
      "1",
      "2",
      "작성자",
      "댓글 내용"
    );

    assertThat(comment.getId()).isNotNull();
    assertThat(comment.getPostId()).isEqualTo("2");
    assertThat(comment.getAuthor()).isEqualTo("작성자");
    assertThat(comment.getContent()).isEqualTo("댓글 내용");
  }

  @Test
  void update() {
    CommentEntity comment = new CommentEntity(
      "1",
      "2",
      "작성자",
      "댓글 내용"
    );

    comment.update("변경된 댓글");

    assertThat(comment.getContent()).isEqualTo("변경된 댓글");
  }
}
