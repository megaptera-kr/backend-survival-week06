package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {
  private PostRepository postRepository;

  private GetPostService getPostService;

  @BeforeEach
  void setUp() {
    postRepository = mock(PostRepository.class);

    getPostService = new GetPostService(postRepository);
  }

  @Test
  @DisplayName("게시물 조회")
  void detail() {
    String postId = "1";
    given(postRepository.find(postId)).willReturn(new PostEntity(
      postId,
      "제목",
      "작성자",
      "내용"
    ));

    PostResponseDto postDto = getPostService.getPostDetail(postId);

    assertThat(postDto.getId()).isEqualTo(postId.toString());
    assertThat(postDto.getTitle()).isEqualTo("제목");
    assertThat(postDto.getAuthor()).isEqualTo("작성자");
    assertThat(postDto.getContent()).isEqualTo("내용");
  }

}
