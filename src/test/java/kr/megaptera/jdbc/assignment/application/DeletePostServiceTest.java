package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.domain.Post;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import kr.megaptera.jdbc.assignment.utils.PostUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {

    private PostDao postDao;
    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        //모의 객체 생성
        postDao = mock(PostDao.class);

        deletePostService = new DeletePostService(postDao);
    }
    @Test
    @DisplayName("게시물 삭제")
    void delete() throws PostNotFound {
        //given
        Post post = Post.builder()
                .id(PostUtil.getId())
                .title("title")
                .content("content")
                .author("author")
                .build();

        given(postDao.find(post.getId())).willReturn(post);

        //when
        deletePostService.deletePost(post.getId());
        //then
        verify(postDao).delete(any());
    }
}
