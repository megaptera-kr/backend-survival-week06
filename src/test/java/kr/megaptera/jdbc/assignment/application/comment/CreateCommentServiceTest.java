package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.dtos.comment.CreateCommentDTO;
import kr.megaptera.jdbc.assignment.model.Comment;
import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {

    private CommentRepository commentRepository;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void createComment() {
        // given
        String postId = "1";
        String author = "author";
        String content = "content";
        CreateCommentDTO createCommentDTO = new CreateCommentDTO(author, content);

        // when
        createCommentService.createComment(postId, createCommentDTO);

        // then
        verify(commentRepository).createComment(any(Comment.class));
    }
}
