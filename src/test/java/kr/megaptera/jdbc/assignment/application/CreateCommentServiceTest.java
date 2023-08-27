package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.applications.CreateCommentService;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateCommentServiceTest {
    private JdbcCommentDao jdbcCommentDao;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        jdbcCommentDao = mock(JdbcCommentDao.class);

        createCommentService = new CreateCommentService(jdbcCommentDao);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        String postId = "001POST";

        CommentCreateDto newComment = new CommentCreateDto("작성자", "댓글 내용");

        createCommentService.createComment(postId, newComment);

        verify(jdbcCommentDao).save(any(Comment.class));
    }
}
