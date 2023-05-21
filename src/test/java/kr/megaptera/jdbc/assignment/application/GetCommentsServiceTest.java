package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {

    private JdbcCommentDao commentDao;

    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);
        getCommentsService = new GetCommentsService(commentDao);
    }

    @Test
    @DisplayName("게시물별 댓글 목록 조회")
    void listByPost() {
        given(commentDao.findByPost("1"))
                .willReturn(
                        List.of(
                                new CommentDto(
                                        "1",
                                        "케이",
                                        "감사합니다!\n운영자님"
                                ),
                                new CommentDto(
                                        "2",
                                        "종희",
                                        "반갑습니다!! 잘 이용할게요."
                                )
                        )
                );

        List<CommentDto> commentDtos = getCommentsService.getComments("1");

        assertThat(commentDtos.size()).isEqualTo(2);
        assertThat(commentDtos.get(0).getAuthor()).contains("케이");
        assertThat(commentDtos.get(1).getContent()).contains("반갑습니다");
    }

}
