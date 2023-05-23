package kr.megaptera.jdbc.assignment.domains.dto;

import kr.megaptera.jdbc.assignment.domains.model.Comment;

public class CommentDto {

    private String id;

    private String author;

    private String content;

    public CommentDto() {
    }

    public CommentDto(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentDto(Comment comment) {
        this.id = comment.id().getId();
        this.author = comment.author().getAuthor();
        this.content = comment.content().toString();
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
