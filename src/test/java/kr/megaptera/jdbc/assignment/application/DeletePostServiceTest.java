package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {
  private PostRepository postRepository;

  private DeletePostService deletePostService;

  @BeforeEach
  void setUp() {
    postRepository = mock(PostRepository.class);

    deletePostService = new DeletePostService(postRepository);
  }

  @Test
  @DisplayName("게시물 삭제")
  void delete() {
    String postId = "1";

    PostEntity post =
      new PostEntity(postId, "제목", "작성자", "내용");

    given(postRepository.find(postId)).willReturn(post);

    deletePostService.deletePost(postId.toString());

    verify(postRepository).delete(any(String.class));
  }
}
