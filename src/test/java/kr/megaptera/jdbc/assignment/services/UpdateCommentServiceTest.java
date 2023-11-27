package kr.megaptera.jdbc.assignment.services;


import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentAuthor;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class UpdateCommentServiceTest {
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

        CommentId commentId = CommentId.of("1");
        PostId postId = PostId.of("1");

        Comment comment = new Comment(
                commentId,
                postId,
                CommentAuthor.of("author"),
                MultilineText.of("content")
        );

        given(commentDao.find(commentId)).willReturn(comment);

        CommentDto commentUpdateDto =
                new CommentDto("updatedComment");

        updateCommentService.updateCommentDto(commentId.toString(), commentUpdateDto);

        assertThat(comment.content().toString()).isEqualTo("updatedComment");

    }
}
