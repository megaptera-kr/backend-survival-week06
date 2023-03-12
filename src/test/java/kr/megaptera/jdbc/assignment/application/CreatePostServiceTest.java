package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostCreateRequestDto;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {
  private PostRepository postRepository;

  private CreatePostService createPostService;

  @BeforeEach
  void setUp() {
    postRepository = mock(PostRepository.class);

    createPostService = new CreatePostService(postRepository);
  }

  @Test
  @DisplayName("게시물 생성")
  void create() {
    PostCreateRequestDto newPost = new PostCreateRequestDto("제목", "작성자", "내용");

    PostResponseDto postDto = createPostService.createPost(newPost);

    assertThat(postDto.getTitle()).isEqualTo("제목");
    assertThat(postDto.getAuthor()).isEqualTo("작성자");
    assertThat(postDto.getContent()).isEqualTo("내용");

    verify(postRepository).save(any(PostEntity.class));
  }
}
