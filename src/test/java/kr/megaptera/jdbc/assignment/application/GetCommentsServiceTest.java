package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {

    private CommentDao commentDao;
    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        commentDao = mock(CommentDao.class);
        getCommentsService = new GetCommentsService(commentDao);
    }


    @Test
    @DisplayName("댓글 목록 조회")
    void list() {
        // given - 모의 객체에 대한 행동을 정의
        given(commentDao.findAll("1")).willReturn(List.of(
                Comment.builder()
                        .id("1")
                        .postId("1")
                        .content("content")
                        .author("author")
                        .build()
        ));

        // when - 테스트할 로직 실행
        List<CommentDto> comments = getCommentsService.getCommentList("1");
        // then - 테스트 결과 검증
        assertThat(comments).hasSize(1);
    }
}
