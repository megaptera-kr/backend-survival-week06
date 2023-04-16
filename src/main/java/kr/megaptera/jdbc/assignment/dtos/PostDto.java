package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.domains.*;

public class PostDto {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostDto() {
    }

    public PostDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(Post post) {
        this.id = post.getId().toString();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
