package kr.megaptera.jdbc.assignment.models;

import kr.megaptera.jdbc.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.comments.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import lombok.Data;

@Data
public class Comment {
    private CommentId id;
    private PostId postId;
    private String author;
    private String content;

    public Comment(CommentEntity entity){
        this.id = new CommentId(entity.getId());
        this.postId = new PostId(entity.getPostId());
        this.author = entity.getAuthor();
        this.content = entity.getContent();
    }

    public Comment(String postId, CommentCreateDto dto){
        this.id = new CommentId();
        this.postId = new PostId(postId);
        this.author = dto.getAuthor();
        this.content = dto.getContent();
    }

    public void update(CommentUpdateDto dto) {
        this.content = dto.getContent();
    }
}
