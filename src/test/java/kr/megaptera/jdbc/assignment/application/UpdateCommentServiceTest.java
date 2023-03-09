package kr.megaptera.jdbc.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.UpdateCommentRequest;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateCommentServiceTest {
    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UpdateCommentService updateCommentService;

    @DisplayName("댓글 수정하기 테스트")
    @Test
    void updateComment() {
        // given
        String postId = "1";
        Post post = new Post(postId, "제목", "Harry", "내용");
        Comment comment = new Comment(postId, "Potter", "좋아요");
        UpdateCommentRequest request = new UpdateCommentRequest("댓글내용바뀜");

        postDao.save(post);
        commentDao.save(comment);

        // when
        updateCommentService.updateComment(comment.getId(), postId, request);
        Comment result = commentDao.find(comment.getId(), postId);

        // then
        assertThat(result.getId()).isEqualTo(comment.getId());
        assertThat(result.getPostId()).isEqualTo(postId);
        assertThat(result.getAuthor()).isEqualTo(comment.getAuthor());
        assertThat(result.getContent()).isEqualTo(request.getContent());
    }

    @AfterEach
    void tearDown() {
        commentDao.clear();
        postDao.clear();
    }
}
