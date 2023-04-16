package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.domains.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import org.junit.jupiter.api.*;

import static org.mockito.BDDMockito.*;

class CommentServiceTest {

    private JdbcCommentDao commentDao;
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        commentDao = mock(JdbcCommentDao.class);

        commentService = new CommentService(commentDao);
    }


    @Test
    void getList() {
//        PostId postId = new PostId("001POST");
//
//        given(commentDao.findAll(postId)).willReturn(List.of(
//                new Comment(
//                        new CommentId("001COMMENT"),
//                        "작성자",
//                        "댓글 내용",
//                        postId
//                )
//        ));
//
//        List<CommentDto> commentDtos
//                = commentService.getList(postId.toString());
//
//        assertThat(commentDtos).hasSize(1);
    }

    @Test
    void createComment() {
        PostId postId = new PostId("001");
        CommentCreateDto commentCreateDto = new CommentCreateDto("author", "content");
        commentService.createComment(postId.toString(), commentCreateDto);
        verify(commentDao).create(any(Comment.class));
    }

    @Test
    void updateComment() {
//        PostId postId = new PostId("001");
//        CommentId commentId = new CommentId("0001");
//        Comment comment = new Comment(commentId, "author", "content", postId);
//
//        CommentDao commentDao = Mockito.mock(CommentDao.class);
//        Mockito.when(commentDao.find(Mockito.any(CommentId.class), Mockito.any(PostId.class)))
//                .thenReturn(comment);
//
//        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("content1");
//
//        CommentService commentService1 = new CommentService(commentDao);
//
//        commentService.updateComment(postId.toString(), commentUpdateDto, commentId.toString());
//        assertThat(comment.content()).isEqualTo("content1");
    }

    @Test
    void deleteComment() {
        PostId postId = new PostId("001");
        CommentId commentId = new CommentId("0001");
        Comment comment = new Comment(commentId, "author", "content", postId);
        commentService.deleteComment(postId.toString(), commentId.toString());
        verify(commentDao).delete(eq(postId.toString()), eq(commentId.toString()));
    }
}