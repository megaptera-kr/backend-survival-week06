package kr.megaptera.jdbc.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    @Test
    void creation() {
        Post post = new Post(
            new PostTitle("title"),
            "작성자",
            new MultilineText("내용")
        );

        assertThat(post.title().toString()).isEqualTo("title");
        assertThat(post.author()).isEqualTo("작성자");
        assertThat(post.content().toString()).isEqualTo("내용");
    }

    @Test
    void update() {
        Post post = new Post(
            new PostId("001POST"),
                new PostTitle("title"),
            "작성자",
            new MultilineText("내용")
        );

        post.update(new PostTitle("change title"), new MultilineText("변경된 내용"));

        assertThat(post.title().toString()).isEqualTo("change title");
        assertThat(post.author()).isEqualTo("작성자");
        assertThat(post.content().toString()).isEqualTo("변경된 내용");
    }
}
