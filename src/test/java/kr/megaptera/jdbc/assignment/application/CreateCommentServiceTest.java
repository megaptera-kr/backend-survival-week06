package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
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

    private JdbcCommentDao commentDao;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);

        createCommentService = new CreateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        String postId = "001POST";

        CommentCreateDto newComment = new CommentCreateDto("작성자", "댓글 내용");

        createCommentService.createComment(postId, newComment);

        verify(commentDao).save(any(Comment.class));
    }
}
