package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetPostsServiceTest {
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
                        new PostId("001POST"),
                        "제목",
                        "작성자",
                        new MultilineText("내용")

                )));

        List<PostDto> postDtos = getPostsService.getPostDtos();

        assertThat(postDtos).hasSize(1);
    }
}
