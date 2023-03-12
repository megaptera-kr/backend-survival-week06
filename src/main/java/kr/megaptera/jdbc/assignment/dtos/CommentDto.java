package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String id;

    private String postId;

    private String author;

    private String content;

    public CommentDto(Comment comment) {
        this(comment.getId().toString(), comment.getPostId().toString(), comment.getAuthor(), comment.getContent());
    }


}
