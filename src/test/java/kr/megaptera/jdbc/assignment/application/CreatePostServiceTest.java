package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        PostDto newPost = new PostDto("제목", "작성자", "내용");

        createPostService.createPost(newPost);

        verify(postDao).save(any(Post.class));
    }
}
