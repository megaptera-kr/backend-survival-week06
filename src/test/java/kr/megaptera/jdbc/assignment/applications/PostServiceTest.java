package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.domains.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class PostServiceTest {

    private JdbcPostDao postDao;

    private PostService postService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);

        postService = new PostService(postDao);
    }

    @Test
    void getList() {
        given(postDao.findAll())
                .willReturn(List.of(new Post(
                        new PostId("001"),
                        "제목",
                        "작성자",
                        "내용"
                )));
        List<PostDto> postDtos = postService.getList();
        assertThat(postDtos).hasSize(1);
    }

    @Test
    void getPost() {
        PostId postId = new PostId("001");
        given(postDao.find(postId.toString()))
                .willReturn(new Post(
                        new PostId("001"),
                        "제목",
                        "작성자",
                        "내용"
                ));
        PostDto postDto = postService.getPost(postId.toString());
        assertThat(postDto.getId()).isEqualTo(postId.toString());
    }

    @Test
    void createPost() {
        PostCreateDto postCreateDto = new PostCreateDto();
        postService.createPost(postCreateDto);
        verify(postDao).create(any(Post.class));
    }

    @Test
    void update() {
        PostId postId = new PostId("001");
        PostUpdateDto postUpdateDto = new PostUpdateDto("title", "content");
        Post post = new Post(postId, "제목", "작성자", "내용");

        postService.update(postId.toString(), postUpdateDto);

        verify(postDao, times(1)).update(eq(postId.toString()), eq(postUpdateDto));
    }

    @Test
    void delete() {
        PostId postId = new PostId("001");
        Post post = new Post(postId, "제목", "작성자", "내용");
        String postIdString = postId.toString();
        postService.delete(postIdString);
        verify(postDao).delete(eq(postIdString));
    }
}