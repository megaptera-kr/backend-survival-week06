package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostAuthor;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPostsServiceTest {
    private JdbcPostDao postDao;

    private GetPostsService getPostsService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);
        getPostsService = new GetPostsService(postDao);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        given(postDao.findAll())
                .willReturn(List.of(
                        new Post(
                                PostId.of("1"),
                                PostTitle.of("title"),
                                PostAuthor.of("author"),
                                MultilineText.of("content")
                        )
                ));

        List<PostDto> postDtos = getPostsService.getPostDtos();

        verify(postDao).findAll();
        assertThat(postDtos).hasSize(1);
        assertThat(postDtos.get(0).getContent()).isEqualTo("content");
    }
}
