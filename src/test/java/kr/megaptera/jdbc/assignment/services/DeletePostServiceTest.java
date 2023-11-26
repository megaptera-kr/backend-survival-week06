package kr.megaptera.jdbc.assignment.services;


import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostAuthor;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class DeletePostServiceTest {
    private JdbcPostDao postDao;
    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);
        deletePostService = new DeletePostService(postDao);
    }

    @Test
    @DisplayName("게시글 삭제")
    void delete() {
        PostId postId = PostId.of("1");

        Post post = new Post(postId, PostTitle.of("title"), PostAuthor.of("author"), MultilineText.of("content"));

        given(postDao.find(postId)).willReturn(post);

        deletePostService.deletePostDto(postId.toString());

        verify(postDao).delete(any(PostId.class));

    }
}
