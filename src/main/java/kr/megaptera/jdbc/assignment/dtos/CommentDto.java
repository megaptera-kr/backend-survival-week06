package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.*;

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
        this(comment.id().toString(), comment.author(), comment.content());
    }

    public CommentDto(String content) {
        this.content = content;
    }

    public CommentDto(String author, String content) {
        this.author = author;
        this.content = content;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
