package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UpdateCommentServiceTest {


    private JdbcCommentDao commentDao;
    private UpdateCommentService updateCommentService;

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

        Comment comment = new Comment(
                commentId,
                postId,
                "작성자1",
                "본문1"
        );

        given(commentDao.find(commentId,postId)).willReturn(comment);

        CommentUpdateDto commentUpdateDto =
                new CommentUpdateDto("변경된 본문");

        updateCommentService.updateComment(commentId.toString(), postId.toString(),commentUpdateDto);

        assertThat(comment.content()).isEqualTo("변경된 본문");

    }
}
