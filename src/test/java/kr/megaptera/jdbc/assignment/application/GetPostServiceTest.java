package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public class GetPostServiceTest {

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
        PostId postId = new PostId("test1");
        given(postDao.find(postId)).willReturn(new Post(
                postId,
                "test2",
                "test3",
                new MultilineText("test4")
        ));

        PostDto postDto = getPostService.getPostDto(postId.toString());

        assertThat(postDto.getId()).isEqualTo(postId.toString());
        assertThat(postDto.getTitle()).isEqualTo("test2");
        assertThat(postDto.getAuthor()).isEqualTo("test3");
        assertThat(postDto.getContent()).isEqualTo("test4");
    }


}
