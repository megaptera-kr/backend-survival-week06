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

class DeletePostServiceTest {

    private PostDao postDao;

    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postDao = mock(PostDao.class);
        deletePostService = new DeletePostService(postDao);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        given(postDao.find(PostId.of("001"))).willReturn(
                new Post(new PostId("001"), "title1", "author1", "content1"));

        PostDto removedPostDto = deletePostService.removePostDto("001");
        assertThat(removedPostDto).isNotNull();
    }
}
