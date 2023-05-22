package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class DeleteCommentServiceTest {
    private JdbcCommentDao commentDao;

    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);

        deleteCommentService = new DeleteCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() {
        CommentId commentId = new CommentId("001COMMENT");

        PostId postId = new PostId("001POST");

        Comment comment = new Comment(commentId, postId, "작성자", "댓글 내용");

        given(commentDao.find(commentId, postId)).willReturn(comment);

        deleteCommentService.deleteComment(
            commentId.toString(),
            postId.toString()
        );

        verify(commentDao).delete(any(CommentId.class));
    }
}
