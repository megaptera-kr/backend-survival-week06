package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreateCommentServiceTest {

    private CommentDao commentDao;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(CommentDao.class);
        createCommentService = new CreateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        String postId = "001";

        CommentDto commentDto = createCommentService.addCommentDto(postId,
                new CommentDto(new Comment(PostId.of(postId), "author1", "content1")));
        assertThat(commentDto).isNotNull();
    }

}
