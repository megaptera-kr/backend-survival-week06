package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class UpdateCommentServiceTest {
    JdbcCommentDao commentDao;

    UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);

        updateCommentService = new UpdateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        CommentId commentId = new CommentId("001COMMENT");
        PostId postId = new PostId("001POST");

        Comment comment = new Comment(commentId, postId, "작성자", "댓글 내용");

        given(commentDao.find(commentId, postId)).willReturn(comment);

        CommentDto commentUpdatedDto
            = new CommentDto("변경된 댓글 내용");

        updateCommentService.updateComment(
            commentId.toString(),
            postId.toString(),
            commentUpdatedDto
        );

        assertThat(comment.content()).isEqualTo("변경된 댓글 내용");
    }
}
