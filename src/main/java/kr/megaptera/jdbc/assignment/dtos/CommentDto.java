package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Comment;

public class CommentDto {
    private String id;
    private String author;
    private String content;

    public CommentDto(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentDto(Comment comment) {
        this(comment.id().toString(), comment.author(), comment.content().toString());
    }


    @Override
    public String toString() {
        return "CommentDto{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
