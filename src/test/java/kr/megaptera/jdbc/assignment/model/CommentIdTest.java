package kr.megaptera.jdbc.assignment.model;

import kr.megaptera.jdbc.assignment.domains.model.CommentId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentIdTest {

    @Test
    void creation() {
        CommentId commentId1 = new CommentId();
        CommentId commentId2 = new CommentId();
        CommentId commentId3 = new CommentId("3");

        assertThat(commentId1.getId()).isNotNull();
        assertThat(commentId2.getId()).isNotNull();
        assertThat(commentId1.getId()).isNotEqualTo(commentId2.getId());
        assertThat(commentId2.getId()).isGreaterThan(commentId1.getId());
        assertThat(commentId3.getId()).contains("3");
    }

}