package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Comment;

public class CreateCommentRequest {
    private String author;
    private String content;


    public CreateCommentRequest() {
    }

    public CreateCommentRequest(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Comment toComment(String postId) {
        return new Comment(postId, author, content);
    }
}
