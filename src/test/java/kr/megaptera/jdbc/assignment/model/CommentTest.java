package kr.megaptera.jdbc.assignment.model;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {
    @Test
    void creation() {
        PostId postId = new PostId("001POST");

        Comment comment = new Comment(
                postId,
                "작성자",
                "댓글 내용"
        );

        assertThat(comment.postId()).isEqualTo(postId);
        assertThat(comment.author()).isEqualTo("작성자");
        assertThat(comment.content()).isEqualTo("댓글 내용");
    }

    @Test
    void update() {
        PostId postId = new PostId("001POST");

        Comment comment = new Comment(
                postId,
                "작성자",
                "댓글 내용"
        );

        comment.update("변경된 댓글");

        assertThat(comment.content()).isEqualTo("변경된 댓글");
    }
}
