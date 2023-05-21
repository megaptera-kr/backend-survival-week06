package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
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

class DeletePostServiceTest {

    private JdbcPostDao postDao;

    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);
        deletePostService = new DeletePostService(postDao);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        given(postDao.find("2"))
                .willReturn(
                        new PostDto(
                                "2",
                                "내가 첫 글???",
                                "김종희",
                                "신난닷!!\n너무 좋아용~~!!"));

        PostDto postDto = deletePostService.deletePost("2");

        verify(postDao).delete(any(String.class));
        assertThat(postDto).isNotNull();
        assertThrows(PostNotFound.class, () -> deletePostService.deletePost("3"));
    }
}
