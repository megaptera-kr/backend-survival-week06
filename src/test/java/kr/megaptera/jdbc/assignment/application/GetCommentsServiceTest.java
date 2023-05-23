package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {
    private JdbcCommentDao jdbcCommentDao;

    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        jdbcCommentDao = mock(JdbcCommentDao.class);

        getCommentsService = new GetCommentsService(jdbcCommentDao);
    }

    @Test
    @DisplayName("댓글 목록 조회")
    void list() {
        PostId postId = new PostId("001POST");

        given(jdbcCommentDao.findAll(postId)).willReturn(List.of(
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
