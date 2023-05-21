package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
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
    @DisplayName("게시물 삭제")
    void delete() {
        PostId postId = new PostId("001POST");

        Post post =
            new Post(postId, "제목", "작성자", new MultilineText("내용"));

        given(postDao.find(postId)).willReturn(post);

        deletePostService.deletePost(postId.toString());

        verify(postDao).delete(any(PostId.class));
    }
}
