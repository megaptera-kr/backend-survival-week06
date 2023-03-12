package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdatePostServiceTest {

    private PostDao postDao;

    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postDao = mock(PostDao.class);
        updatePostService = new UpdatePostService(postDao);
    }
    @Test
    @DisplayName("게시물 수정")
    void update() {
        given(postDao.find(PostId.of("001")))
                .willReturn(new Post(new PostId("001"), "title1", "author1", "content1"));

        PostDto updatedPostDto = updatePostService.updatePostDto("001",
                new PostDto(new Post(new PostId("001"), "title2", "author1", "content2")));
        assertThat(updatedPostDto).isNotNull();
    }

}
