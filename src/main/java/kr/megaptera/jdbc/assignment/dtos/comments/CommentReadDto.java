package kr.megaptera.jdbc.assignment.dtos.comments;

import kr.megaptera.jdbc.assignment.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentReadDto {
    private String id;
    private String author;
    private String content;

    public CommentReadDto(Comment comment){
        this.id = comment.getId().toString();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
    }
}
