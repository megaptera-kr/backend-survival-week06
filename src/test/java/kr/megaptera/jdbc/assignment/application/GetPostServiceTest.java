package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

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
                new PostTitle("title"),
            "작성자",
            new MultilineText("내용")
        ));

        PostDto postDto = getPostService.getPostDto(postId.toString());

        assertThat(postDto.getId()).isEqualTo(postId.toString());
        assertThat(postDto.getTitle()).isEqualTo("title");
        assertThat(postDto.getAuthor()).isEqualTo("작성자");
        assertThat(postDto.getContent()).isEqualTo("내용");
    }
}
