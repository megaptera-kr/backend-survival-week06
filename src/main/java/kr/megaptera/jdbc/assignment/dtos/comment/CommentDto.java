package kr.megaptera.jdbc.assignment.dtos.comment;

import kr.megaptera.jdbc.assignment.models.comment.*;

public class CommentDto {

    private String id;
    private String postId;
    private String author;
    private String content;

    public CommentDto(String id, String postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentDto(String postId, String author, String content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentDto(Comment comment) {
        this(
                comment.commentId().toString(),
                comment.postId().toString(),
                comment.author(),
                comment.content()
        );
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
