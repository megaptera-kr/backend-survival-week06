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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPostServiceTest {
    private JdbcPostDao postDao;
    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);
        getPostService = new GetPostService(postDao);
    }

    @Test
    @DisplayName("게시물 상세 조회")
    void detail() {

        PostId postId = new PostId("1");
        Post samplePost = new Post(postId,
                PostTitle.of("title"),
                PostAuthor.of("author"),
                MultilineText.of("content"));
        given(postDao.find(postId)).willReturn(
                samplePost);

        PostDto postDto = getPostService.getPostDto(postId.toString());

        verify(postDao).find(postId);
        assertThat(postDto.getId()).isEqualTo(postId.toString());
        assertThat(postDto.getTitle()).isEqualTo(samplePost.title().toString());
        assertThat(postDto.getAuthor()).isEqualTo(samplePost.author().toString());
        assertThat(postDto.getContent()).isEqualTo(samplePost.content().toString());

    }
}
