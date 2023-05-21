package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.domains.dto.PostUpdateDto;
import kr.megaptera.jdbc.assignment.domains.model.Post;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdatePostServiceTest {

    private JdbcPostDao postDao;

    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);
        updatePostService = new UpdatePostService(postDao);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() {
        given(postDao.find("2"))
                .willReturn(
                        new PostDto(
                                "2",
                                "내가 첫 글???",
                                "김종희",
                                "신난닷!!\n너무 좋아용~~!!"));

        PostUpdateDto postUpdateDto = new PostUpdateDto("새로 글 쓰기", "새로 글을\n쓰는 중입니다.\n\n좋아용");

        PostDto postDto = updatePostService.updatePost("2", postUpdateDto);

        verify(postDao).save(any(Post.class));
        assertThat(postDto).isNotNull();
        assertThat(postDto.getTitle()).contains("새로");
        assertThrows(PostNotFound.class, () -> updatePostService.updatePost("3", postUpdateDto));
    }
}
