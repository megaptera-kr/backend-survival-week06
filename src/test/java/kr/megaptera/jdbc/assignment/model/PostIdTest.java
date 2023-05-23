package kr.megaptera.jdbc.assignment.model;

import kr.megaptera.jdbc.assignment.domains.model.PostId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostIdTest {

    @Test
    void creation() {
        PostId postId1 = new PostId();
        PostId postId2 = new PostId();
        PostId postId3 = new PostId("3");

        assertThat(postId1.getId()).isNotNull();
        assertThat(postId2.getId()).isNotNull();
        assertThat(postId1.getId()).isNotEqualTo(postId2.getId());
        assertThat(postId2.getId()).isGreaterThan(postId1.getId());
        assertThat(postId3.getId()).contains("3");
    }

}