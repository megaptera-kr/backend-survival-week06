package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreatePostServiceTest {

    private JdbcPostDao postDao;

    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);

        createPostService = new CreatePostService(postDao);
    }

    @Test
    @DisplayName("포스트생성")
    void create() {
        PostDto newPost = new PostDto("test1","test11", "test111", "test1111");
        createPostService.createPost(newPost);

        verify(postDao).save(any(Post.class));

    }

}
