package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UpdateCommentServiceTest {
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
        CommentId commentId = new CommentId("test1");
        PostId postId = new PostId("test2");

        Comment comment = new Comment(commentId, postId, "test3", MultilineText.of("4test"));

        given(commentDao.find(commentId)).willReturn(comment);

        CommentDto commentDto = new CommentDto(commentId.toString(), comment.author(), "newTest");

        updateCommentService.updateComment(
                commentId.toString(),
                commentDto
        );

        assertThat(comment.content().toString()).isEqualTo("newTest");
    }
}
