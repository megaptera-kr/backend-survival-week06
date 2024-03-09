package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {
    private CommentRepository commentRepository;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() {
        // given
        String id = "1";
        String postId = "1";

        // when
        deleteCommentService.deleteComment(id, postId);

        // then
         verify(commentRepository).deleteComment(id, postId);
    }
}
