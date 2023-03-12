package kr.megaptera.jdbc.assignment.application;


import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCommentServiceTest {

    private CommentDao commentDao;

    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(CommentDao.class);
        deleteCommentService = new DeleteCommentService(commentDao);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        String postId = "001";
        String commentId = "0001";
        given(commentDao.find(CommentId.of(commentId)))
                .willReturn(new Comment(new CommentId(commentId), PostId.of(postId), "author1", "content1"));

        CommentDto removedCommentDto = deleteCommentService.removeCommentDto(commentId);
        assertThat(removedCommentDto).isNotNull();
    }

}
