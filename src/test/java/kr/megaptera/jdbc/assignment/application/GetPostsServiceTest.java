package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.daos.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostsServiceTest {
  private PostRepository postRepository;

  private GetPostsService getPostsService;

  @BeforeEach
  void setUp() {
    postRepository = mock(PostRepository.class);

    getPostsService = new GetPostsService(postRepository);
  }

  @Test
  @DisplayName("게시물 목록 조회")
  void list() {
    given(postRepository.findAll())
      .willReturn(List.of(new PostEntity(
        "1",
        "제목",
        "작성자",
        "내용"
      )));

    List<PostResponseDto> postDtos = getPostsService.getPosts();

    assertThat(postDtos).hasSize(1);
  }
}
