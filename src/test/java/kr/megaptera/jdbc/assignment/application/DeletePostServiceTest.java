

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

public class DeletePostServiceTest {
    private JdbcPostDao postDao;

    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);

        deletePostService = new DeletePostService(postDao);
    }

    @Test
    @DisplayName("포스트 삭제")
    void delete() {
        PostId postId = new PostId("test1");

        Post post = new Post(postId, "test2", "test3", new MultilineText("test4"));

        given(postDao.find(postId)).willReturn(post);

        deletePostService.deletePost(postId.toString());

        verify(postDao).delete(any(PostId.class));
    }
}
