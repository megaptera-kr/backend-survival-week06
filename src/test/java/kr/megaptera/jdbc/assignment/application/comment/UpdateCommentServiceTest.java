package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.dtos.comment.UpdateCommentDTO;
import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdateCommentServiceTest {
    private CommentRepository commentRepository;
    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 수정 성공")
    void updateComment() {
        // given
        String commentId = "1";
        String postId = "1";
        String content = "수정된 댓글";
        UpdateCommentDTO updateCommentDTO = new UpdateCommentDTO(content);

        // when
        updateCommentService.updateComment(commentId, postId, updateCommentDTO);

        // then
        verify(commentRepository).updateComment(commentId, postId, content);
    }
}
