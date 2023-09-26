package kr.megaptera.jdbc.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {

    @Test
    void creation() {
        Post post = new Post(
                "제목",
                "작성자",
                new MultilineText("본문")
        );

        assertThat(post.title()).isEqualTo("제목");
        assertThat(post.author()).isEqualTo("작성자");
        assertThat(post.content().toString()).isEqualTo("본문");
    }

    @Test
    void update() {
        Post post = new Post(
                "제목",
                "작성자",
                MultilineText.of("본문")
        );

        post.update("변경된 제목",new MultilineText("변경된 본문"));

        assertThat(post.title()).isEqualTo("변경된 제목");
        assertThat(post.author()).isEqualTo("작성자");
        assertThat(post.content().toString()).isEqualTo("변경된 본문");
    }

}
