package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteCommentServiceTest {
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
        CommentId commentId = new CommentId("test1");

        PostId postId = new PostId("test2");

        Comment comment = new Comment(commentId, postId, "test3", MultilineText.of("test44"));

        given(commentDao.find(commentId)).willReturn(comment);

        deleteCommentService.deleteComment(commentId.toString());

        verify(commentDao).delete(any(CommentId.class));
    }

}
