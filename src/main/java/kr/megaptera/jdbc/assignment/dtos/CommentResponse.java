package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Comment;

public class CommentResponse {
    private String id;
    private String postId;
    private String author;
    private String content;

    public CommentResponse(String id, String postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentResponse(Comment comment) {
        this(comment.getId(), comment.getPostId(), comment.getAuthor(), comment.getContent());
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
