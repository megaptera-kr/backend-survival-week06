package kr.megaptera.jdbc.assignment.entities;

import kr.megaptera.jdbc.assignment.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentEntity {
    private String id;
    private String postId;
    private String author;
    private String content;

    public CommentEntity(Comment comment){
        this.id = comment.getId().toString();
        this.postId = comment.getPostId().toString();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
    }
}
