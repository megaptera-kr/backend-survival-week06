package kr.megaptera.jdbc.assignment.model;

import kr.megaptera.jdbc.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.jdbc.assignment.domains.dto.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.domains.model.Comment;
import kr.megaptera.jdbc.assignment.domains.model.CommentId;
import kr.megaptera.jdbc.assignment.domains.model.PostId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {

    @Test
    void creation() {
        CommentCreateDto CommentCreateDto = new CommentCreateDto("글쓴이입니다", "내용입니다\n\n내용");
        Comment Comment = new Comment(PostId.of("3"), CommentCreateDto);

        assertThat(Comment.id()).isNotNull();
        assertThat(Comment.postId().getId()).contains("3");
        assertThat(Comment.author().getAuthor()).contains("글쓴이");
        assertThat(Comment.content().toString()).contains("내용");
    }

    @Test
    void updateComment() {
        CommentCreateDto CommentCreateDto = new CommentCreateDto("글쓴이입니다", "내용입니다\n\n내용");
        Comment Comment = new Comment(PostId.of("3"), CommentCreateDto);
        CommentId CommentId = Comment.id();

        CommentUpdateDto CommentUpdateDto = new CommentUpdateDto("새로운 내용~입니닷");
        Comment.updateComment(CommentUpdateDto);

        assertThat(Comment.id().getId()).isEqualTo(CommentId.getId());
        assertThat(Comment.postId().getId()).contains("3");
        assertThat(Comment.author().getAuthor()).contains("글쓴이");
        assertThat(Comment.content().toString()).contains("~입니닷");
    }
}