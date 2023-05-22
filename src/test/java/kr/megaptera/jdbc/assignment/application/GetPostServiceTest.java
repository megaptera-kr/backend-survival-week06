package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class GetPostServiceTest {
    private JdbcPostDao postDao;

    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);

        getPostService = new GetPostService(postDao);
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() {
        PostId postId = new PostId("POST0001");
        given(postDao.find(postId)).willReturn(new Post(
            postId,
            "제목",
            "작성자",
            new MultilineText("내용")
        ));

        PostDto postDto = getPostService.getPostDto(postId.toString());

        assertThat(postDto.getId()).isEqualTo(postId.toString());
        assertThat(postDto.getTitle()).isEqualTo("제목");
        assertThat(postDto.getAuthor()).isEqualTo("작성자");
        assertThat(postDto.getContent()).isEqualTo("내용");
    }
}
