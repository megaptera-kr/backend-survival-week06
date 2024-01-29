package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.domain.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentDto {

    private String id;

    private String author;

    private String content;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
    }

}
