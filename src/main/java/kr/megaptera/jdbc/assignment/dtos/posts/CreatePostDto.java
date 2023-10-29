package kr.megaptera.jdbc.assignment.dtos.posts;

public class CreatePostDto {
    String title;
    String author;
    String content;

    public CreatePostDto(String title, String author, String content) {
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
