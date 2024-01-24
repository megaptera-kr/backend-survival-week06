package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import kr.megaptera.jdbc.assignment.utils.PostUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {

    private CommentDao commentDao;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(CommentDao.class);
        deleteCommentService = new DeleteCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() throws CommentNotFound {
        //given
        Comment comment = Comment.builder()
                .id(PostUtil.getId())
                .postId("1")
                .content("content")
                .author("author")
                .build();

        given(commentDao.find(comment.getId(), comment.getPostId())).willReturn(comment);

        //when
        deleteCommentService.deleteComment(comment.getId(), comment.getPostId());

        //then
        verify(commentDao).delete(any());
    }

}
