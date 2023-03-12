package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {

    private CommentDao commentDao;

    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(CommentDao.class);
        updateCommentService = new UpdateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        String postId = "001";
        String commentId = "0001";
        given(commentDao.find(CommentId.of(commentId)))
                .willReturn(new Comment(new CommentId(commentId), PostId.of(postId), "author1", "content1"));

        CommentDto updatedCommentDto = updateCommentService.updateCommentDto(commentId, postId,
                new CommentDto(new Comment(PostId.of(postId), "author1", "content2")));
        assertThat(updatedCommentDto).isNotNull();
    }

}
