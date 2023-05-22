package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

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
            .willReturn(List.of(new Post(
                new PostId("0001POST"),
                "제목",
                "작성자",
                new MultilineText("내용")
            )));

        List<PostDto> postDtos = getPostsService.getPostDtos();

        assertThat(postDtos).hasSize(1);
    }
}
