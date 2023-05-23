package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class GetCommentsServiceTest {
    private JdbcCommentDao commentDao;

    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);

        getCommentsService = new GetCommentsService(commentDao);
    }

    @Test
    @DisplayName("댓글 목록 조회")
    void list() {
        PostId postId = new PostId("001POST");

        given(commentDao.findAll(postId)).willReturn(List.of(
            new Comment(
                new CommentId("001COMMENT"),
                postId,
                "작성자",
                "댓글 내용"
            )
        ));

        List<CommentDto> commentDtos
            = getCommentsService.getCommentDtos(postId.toString());

        assertThat(commentDtos).hasSize(1);
    }
}
