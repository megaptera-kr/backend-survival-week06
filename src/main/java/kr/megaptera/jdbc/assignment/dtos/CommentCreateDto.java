package kr.megaptera.jdbc.assignment.dtos;

public class CommentCreateDto {
    private String author;
    private String content;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
