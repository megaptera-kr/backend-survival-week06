package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.domain.Post;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {

    private PostDao postDao;
    private GetPostService getPostService;
    @BeforeEach
    void setUp() {
        postDao = mock(PostDao.class);
        getPostService = new GetPostService(postDao);
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() throws PostNotFound {
        //given
        given(postDao.find("1")).willReturn(
                Post.builder()
                        .id("1")
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());

        //when
        PostDto postDto = getPostService.getPost("1");

        //then
        assertThat(postDto.getContent()).isEqualTo("content");
        assertThat(postDto.getAuthor()).isEqualTo("author");
        assertThat(postDto.getTitle()).isEqualTo("title");
    }
}
