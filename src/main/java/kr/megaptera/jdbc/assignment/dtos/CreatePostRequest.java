package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Post;

public class CreatePostRequest {
    private String title;
    private String author;
    private String content;

    public CreatePostRequest() {
    }

    public CreatePostRequest(String title, String author, String content) {
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

    public Post toPost() {
        return new Post(title, author, content);
    }
}
