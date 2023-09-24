package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

public class CreateCommentServiceTest {
    private JdbcCommentDao commentDao;
    public CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);
        createCommentService = new CreateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 생성")
    void  create() {
        String postId = "001COMMENT";

        CommentCreateDto commentCreateDto = new CommentCreateDto(
                "작성자",
                "댓글 내용"
        );

        createCommentService.createComment(postId, commentCreateDto);

        verify(commentDao).save(any(Comment.class));


    }
}
