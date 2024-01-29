package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import kr.megaptera.jdbc.assignment.utils.PostUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {

    private CommentDao commentDao;

    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        //모의 객체 생성
        commentDao = mock(CommentDao.class);
        updateCommentService = new UpdateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() throws CommentNotFound {
        //given
        Comment comment = Comment.builder()
                .id(PostUtil.getId())
                .postId("1")
                .content("content")
                .author("author")
                .build();

        Comment updatedComment = Comment.builder()
                .content("updatedContent")
                .build();

        given(commentDao.find(comment.getId(), comment.getPostId())).willReturn(comment);
        //when
        updateCommentService.updateComment(comment.getId(), comment.getPostId(), new CommentDto(updatedComment));
        //then
        assertThat(comment.getContent()).isEqualTo(updatedComment.getContent());

    }
}
