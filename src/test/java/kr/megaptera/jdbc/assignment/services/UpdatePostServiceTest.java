package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostAuthor;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

        PostId postId = new PostId("1");

        Post post = new Post(postId, PostTitle.of("title"), PostAuthor.of("author"), MultilineText.of("content"));

        given(postDao.find(postId)).willReturn(post);

        PostDto postUpdateDto =
                new PostDto("updatedTitle", "updatedContent");

        updatePostService.updatePostDto(postId.toString(), postUpdateDto);

        verify(postDao).update(any(Post.class));
        assertThat(post.title().toString()).isEqualTo("updatedTitle");
        assertThat(post.content()).isEqualTo(MultilineText.of("updatedContent"));

    }
}
