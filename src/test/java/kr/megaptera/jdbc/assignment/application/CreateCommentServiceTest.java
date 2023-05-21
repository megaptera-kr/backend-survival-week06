package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {

    private CommentDao commentDao;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(CommentDao.class);

        createCommentService = new CreateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        String postId = "001POST";

        CommentCreateDto commentCreateDto = new CommentCreateDto("호진", "내용");

        CommentDto commentDto = createCommentService.createComment(postId, commentCreateDto);

        assertThat(commentDto.getAuthor()).isEqualTo("호진");
        assertThat(commentDto.getContent()).isEqualTo("내용");

        verify(commentDao).save(any(Comment.class));
    }
}
