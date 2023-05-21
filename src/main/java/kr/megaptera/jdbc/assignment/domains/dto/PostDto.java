package kr.megaptera.jdbc.assignment.domains.dto;

import kr.megaptera.jdbc.assignment.domains.model.Post;

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
        this.id = post.id().getId();
        this.content = post.content().toString();
        this.author = post.author().getAuthor();
        this.title = post.title().getTitle();
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
}
