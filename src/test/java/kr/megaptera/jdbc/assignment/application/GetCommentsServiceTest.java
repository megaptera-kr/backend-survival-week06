package kr.megaptera.jdbc.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.CommentResponse;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetCommentsServiceTest {
    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private GetCommentsService getCommentsService;

    @DisplayName("하나의 게시물에 대한 댓글 다수 가져오기 테스트")
    @Test
    void getComments() {
        // given
        String postId = "1";
        Post post = new Post(postId, "제목", "Harry", "내용");
        Comment comment1 = new Comment(postId, "Potter", "좋아요");
        Comment comment2 = new Comment(postId, "Ron", "저도 좋아요");

        postDao.save(post);
        commentDao.save(comment1);
        commentDao.save(comment2);

        // when
        List<CommentResponse> comments = getCommentsService.getComments(postId);

        // then
        assertThat(comments.size()).isEqualTo(2);
    }

    @AfterEach
    void tearDown() {
        commentDao.clear();
        postDao.clear();
    }

}
