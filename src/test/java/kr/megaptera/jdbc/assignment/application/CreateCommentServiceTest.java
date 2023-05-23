package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.jdbc.assignment.domains.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("게시물에 댓글 생성")
    void createComment() {
        CommentCreateDto commentCreateDto = new CommentCreateDto("운영자", "감사합니다. 앞으로 자주 이용해 주세요.");
        createCommentService.createComment("1", commentCreateDto);

        verify(commentDao).save(any(String.class), any(Comment.class));
    }

}
