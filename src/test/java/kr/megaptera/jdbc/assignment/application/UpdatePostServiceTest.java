package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.applications.UpdatePostService;
import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdatePostServiceTest {
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
        PostId postId = new PostId("001POST");

        Post post =
                new Post(postId, "제목", "작성자", new MultilineText("내용"));

        given(postDao.find(postId)).willReturn(post);

        PostUpdateDto postUpdateDto =
                new PostUpdateDto("변경된 제목", "변경된 내용");

        updatePostService.updatePost(postId.toString(), postUpdateDto);

        assertThat(post.title()).isEqualTo("변경된 제목");
        assertThat(post.content()).isEqualTo(new MultilineText("변경된 내용"));
    }
}