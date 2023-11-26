package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {
    private JdbcCommentDao commentDao;
    public CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);
        createCommentService = new CreateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        String postId = "1";

        CommentDto commentCreateDto = new CommentDto(
                "author",
                "content"
        );

        createCommentService.createCommentDto(postId, commentCreateDto);

        verify(commentDao).save(any(Comment.class));
    }
}
