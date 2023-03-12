package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreatePostServiceTest {

    private PostDao postDao;

    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postDao = mock(PostDao.class);
        createPostService = new CreatePostService(postDao);
    }


    @Test
    @DisplayName("게시물 생성")
    void create() {
        PostDto postDto = createPostService.addPostDto(
                new PostDto(new Post("title1", "author1", "content1")));
        assertThat(postDto).isNotNull();
    }
}
