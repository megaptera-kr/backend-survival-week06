package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetCommentServiceTest {
    private CommentRepository commentRepository;
    private GetCommentService getCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        getCommentService = new GetCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 목록을 조회한다.")
    void getComments() {
        // given
        String postId = "1";

        // when
        getCommentService.getComments(postId);

        // then
        verify(commentRepository).getComments(postId);
    }
}
