package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CreateCommentServiceTest {
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

        CommentDto newComment = new CommentDto("작성자", "댓글 내용");

        createCommentService.create(postId, newComment);

        verify(jdbcCommentDao).save(any(Comment.class));
    }
}
