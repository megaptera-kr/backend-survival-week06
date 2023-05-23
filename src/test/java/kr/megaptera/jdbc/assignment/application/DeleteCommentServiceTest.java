package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
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

class DeleteCommentServiceTest {

    private JdbcCommentDao commentDao;

    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);
        deleteCommentService = new DeleteCommentService(commentDao);
    }

    @Test
    @DisplayName("게시물에 이미 존재하는 댓글 삭제")
    void delete() {
        given(commentDao.find("3", "1"))
                .willReturn(
                        new CommentDto(
                                "3",
                                "케이",
                                "감사합니다!\n운영자님"
                        )
                );

        CommentDto commentDto = deleteCommentService.deleteComment("3", "1");

        verify(commentDao).delete(any(String.class), any(String.class));

        assertThat(commentDto.getContent()).contains("운영자님");
        assertThrows(CommentNotFound.class, () -> deleteCommentService.deleteComment("4", "1"));
        assertThrows(CommentNotFound.class, () -> deleteCommentService.deleteComment("3", "10"));
    }

}
