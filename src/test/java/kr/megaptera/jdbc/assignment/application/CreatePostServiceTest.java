package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {
    private JdbcPostDao postDao;

    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);

        createPostService = new CreatePostService(postDao);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        PostCreateDto postCreateDto = new PostCreateDto("제목", "작성자", "내용");

        PostDto postDto = createPostService.createPost(postCreateDto);

        assertThat(postDto.getTitle()).isEqualTo("제목");
        assertThat(postDto.getAuthor()).isEqualTo("작성자");
        assertThat(postDto.getContent()).isEqualTo("내용");

        verify(postDao).save(any(Post.class));
    }
}
