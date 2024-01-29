package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.domain.Post;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdatePostServiceTest {

    private PostDao postDao;
    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        //모의 객체 생성
        postDao = mock(PostDao.class);
        updatePostService = new UpdatePostService(postDao);
    }
    @Test
    @DisplayName("게시물 수정")
    void updatePost() throws PostNotFound {
        //given
        Post post = Post.builder()
                .id("1")
                .title("title")
                .content("content")
                .build();

        Post updatedPost = Post.builder()
                .title("updated title")
                .content("updated content")
                .build();

        given(postDao.find(post.getId())).willReturn(post);

        // when
        updatePostService.updatePost(post.getId(), new PostDto(updatedPost));

        // then
        assertThat(post.getTitle()).isEqualTo(updatedPost.getTitle());
        assertThat(post.getContent()).isEqualTo(updatedPost.getContent());

    }
}
