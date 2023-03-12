package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
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
    @DisplayName("ㄷㅐㅅ글생성")
    void create() {
        String postId = "test2";

        CommentDto newComment = new CommentDto("test1", "test11", "test111");
        createCommentService.createComment(newComment, postId);

        verify(jdbcCommentDao).save(any(Comment.class));
    }


}
