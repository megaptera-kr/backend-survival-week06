package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.domains.dto.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.domains.model.Comment;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdateCommentServiceTest {

    private JdbcCommentDao commentDao;

    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);
        updateCommentService = new UpdateCommentService(commentDao);
    }

    @Test
    @DisplayName("게시물에 이미 존재하는 댓글 수정")
    void updateComment() {
        given(commentDao.find("3", "1"))
                .willReturn(
                        new CommentDto(
                                "3",
                                "케이",
                                "감사합니다!\n운영자님"
                        )
                );

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("아차, 수정하겠습니다.\n또 만나요~");
        CommentDto commentDto = updateCommentService.updateComment("3", "1", commentUpdateDto);

        verify(commentDao).update(any(String.class), any(String.class), any(Comment.class));
        assertThat(commentDto.getContent()).contains("수정하겠습니다");
        assertThrows(CommentNotFound.class, () -> updateCommentService.updateComment("4", "1", commentUpdateDto));
        assertThrows(CommentNotFound.class, () -> updateCommentService.updateComment("3", "10", commentUpdateDto));
    }

}
