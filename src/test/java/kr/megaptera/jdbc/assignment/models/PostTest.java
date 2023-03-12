package kr.megaptera.jdbc.assignment.models;

import kr.megaptera.jdbc.assignment.entities.PostEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
  @Test
  void creation() {
    PostEntity post = new PostEntity(
      "1",
      "제목",
      "작성자",
      "내용"
    );

    assertThat(post.getId()).isNotNull();
    assertThat(post.getTitle()).isEqualTo("제목");
    assertThat(post.getAuthor()).isEqualTo("작성자");
    assertThat(post.getContent()).isEqualTo("내용");
  }

  @Test
  void update() {
    PostEntity post = new PostEntity(
      "1",
      "제목",
      "작성자",
      "내용"
    );

    post.update("변경된 제목", "변경된 내용");

    assertThat(post.getTitle()).isEqualTo("변경된 제목");
    assertThat(post.getAuthor()).isEqualTo("작성자");
    assertThat(post.getContent()).isEqualTo("변경된 내용");
  }
}
