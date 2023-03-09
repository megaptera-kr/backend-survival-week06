package kr.megaptera.jdbc.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;

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
public class CreateCommentServiceTest {

    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CreateCommentService createCommentService;

    @DisplayName("댓글 생성 테스트")
    @Test
    void createComment() {
        // given
        String postId = "1";
        Post post = new Post(postId, "제목", "Harry", "내용");
        Comment comment = new Comment(postId, "Potter", "좋아요");
        postDao.save(post);

        // when
        CommentResponse result = createCommentService.createComment(comment);

        // then
        assertThat(result.getId()).isEqualTo(comment.getId());
        assertThat(result.getPostId()).isEqualTo(postId);
        assertThat(result.getAuthor()).isEqualTo(comment.getAuthor());
        assertThat(result.getContent()).isEqualTo(comment.getContent());
    }

    @AfterEach
    void tearDown() {
        commentDao.clear();
        postDao.clear();
    }
}
