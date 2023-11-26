package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentAuthor;
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
import static org.mockito.Mockito.verify;


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
        PostId postId = PostId.of("1");
        given(commentDao.findAll(postId.toString()))
                .willReturn(List.of(
                        new Comment(
                                CommentId.of("COMMENT_1"),
                                postId,
                                CommentAuthor.of("AUTHOR_1"),
                                MultilineText.of("CONTENT_1")
                        ),
                        new Comment(
                                CommentId.of("COMMENT_2"),
                                postId,
                                CommentAuthor.of("AUTHOR_2"),
                                MultilineText.of("CONTENT_2")
                        )
                ));

        List<CommentDto> commentDtos = getCommentsService.getCommentDtos(postId.toString());

        verify(commentDao.findAll(postId.toString()));
        assertThat(commentDtos).hasSize(2);
        assertThat(commentDtos.get(0).getContent()).isEqualTo("CONTENT_1");
        assertThat(commentDtos.get(1).getContent()).isEqualTo("CONTENT_2");
    }
}
