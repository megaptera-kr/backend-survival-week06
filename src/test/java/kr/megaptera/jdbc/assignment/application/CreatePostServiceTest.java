package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.domain.Post;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {

    private PostDao postDao;

    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        //모의 객체 생성
        postDao = mock(PostDao.class);

         createPostService = new CreatePostService(postDao);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        //given
        Post post = Post.builder()
                .id("1")
                .title("title")
                .content("content")
                .author("author")
                .build();

        //when
        createPostService.createPost(new PostDto(post));

        //then
        verify(postDao).createPostSave(any(Post.class));
    }
}
