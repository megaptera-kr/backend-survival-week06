package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
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
    @DisplayName("임의의 게시물 하나에 대한 댓글 목록 조회")
    void list() {
        String postId = "001";
        given(commentDao.findByPostId(postId)).willReturn(List.of(
                new Comment(PostId.of("001"), "author1", "content1"),
                new Comment(PostId.of("001"), "author2", "content2")
        ));

        List<CommentDto> commentDtos = getCommentsService.getCommentDtos(postId);
        assertThat(commentDtos).hasSize(2);
    }

}
