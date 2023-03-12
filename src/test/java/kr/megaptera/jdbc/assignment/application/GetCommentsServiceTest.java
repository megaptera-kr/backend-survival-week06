package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetCommentsServiceTest {
    private JdbcCommentDao commentDao;

    private GetCommentsService getCommentsService;



    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);

        getCommentsService = new GetCommentsService(commentDao);
    }

    @Test
    @DisplayName("댓글 목록 조회as")
    void list() {
        PostId postId = new PostId("test1");

        given(commentDao.findAll(postId)).willReturn(List.of(
                new Comment(
                        new CommentId("test2"),
                        postId,
                        "test3",
                        MultilineText.of("test4")
                ),
                new Comment(
                        new CommentId("test5"),
                        postId,
                        "test6",
                        MultilineText.of("test7")
                )
        ));

        List<CommentDto> commentDtos = getCommentsService.getCommentDto(postId.toString());

        assertThat(commentDtos).hasSize(2);
    }

}
