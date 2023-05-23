package kr.megaptera.jdbc.assignment.model;

import kr.megaptera.jdbc.assignment.domains.dto.PostCreateDto;
import kr.megaptera.jdbc.assignment.domains.dto.PostUpdateDto;
import kr.megaptera.jdbc.assignment.domains.model.Post;
import kr.megaptera.jdbc.assignment.domains.model.PostId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @Test
    void creation() {
        PostCreateDto postCreateDto = new PostCreateDto("제목입니다", "글쓴이입니다", "내용입니다\n\n내용");
        Post post = new Post(postCreateDto);

        assertThat(post.id()).isNotNull();
        assertThat(post.title().getTitle()).contains("제목");
        assertThat(post.author().getAuthor()).contains("글쓴이");
        assertThat(post.content().toString()).contains("내용");
    }

    @Test
    void updatePost() {
        PostCreateDto postCreateDto = new PostCreateDto("제목입니다", "글쓴이입니다", "내용입니다\n\n내용");
        Post post = new Post(postCreateDto);
        PostId postId = post.id();

        PostUpdateDto postUpdateDto = new PostUpdateDto("새로운 제목입니다", "새로운 내용~입니닷");
        post.updatePost(postUpdateDto);

        assertThat(post.id().getId()).isEqualTo(postId.getId());
        assertThat(post.title().getTitle()).contains("새로운");
        assertThat(post.author().getAuthor()).contains("글쓴이");
        assertThat(post.content().toString()).contains("~입니닷");
    }
}