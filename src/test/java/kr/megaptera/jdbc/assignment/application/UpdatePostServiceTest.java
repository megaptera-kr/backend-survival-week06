package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;

import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UpdatePostServiceTest {
    private JdbcPostDao postDao;

    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);

        updatePostService = new UpdatePostService(postDao);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() {
        PostId postId = new PostId("test1");

        Post post = new Post(postId, "test2", "test3", new MultilineText("test4"));

        given(postDao.find(postId)).willReturn(post);

        PostDto postDto = new PostDto("test1", "test5", "test3", "test6");

        updatePostService.updatePost(postId.toString(), postDto);

        assertThat(post.title()).isEqualTo("test5");
        assertThat(post.content().toString()).isEqualTo(new MultilineText("test6").toString());
    }

}
