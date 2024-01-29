package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreateCommentServiceTest {

    private JdbcCommentDao commentDao;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);
        createCommentService = new CreateCommentService(commentDao);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        //given
        Comment comment = Comment.builder()
                .id("1")
                .postId("1")
                .content("content")
                .author("author")
                .build();

        //when
        CommentDto commentDto = createCommentService.createComment(comment.getPostId(), new CommentDto(comment));

        //then
        assertThat(commentDto.getContent()).isEqualTo("content");
        assertThat(commentDto.getAuthor()).isEqualTo("author");
    }

}
