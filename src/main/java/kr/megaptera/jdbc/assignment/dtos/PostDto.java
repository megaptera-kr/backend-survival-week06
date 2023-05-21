package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Post;

public class PostDto {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(Post post) {
        this(post.id().toString(), post.title().toString(), post.author(), post.content().toString());
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
