package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.*;

public class PostDto {
    private String id;

    private String title;

    private String author;

    private String content;

    public PostDto() {
    }

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(Post post) {
        this(post.id().toString(), post.title(),
                post.author(), post.content().toString());
    }

    public PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
