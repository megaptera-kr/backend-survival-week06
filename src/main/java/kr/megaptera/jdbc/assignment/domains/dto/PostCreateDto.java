package kr.megaptera.jdbc.assignment.domains.dto;

public class PostCreateDto {

    private String title;
    private String author;
    private String content;

    public PostCreateDto() {
    }

    public PostCreateDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
