package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Post;

public class PostDto {

    private String id;

    private String title;

    private String author;

    private String content;

    public PostDto() {
    }

    public PostDto(Post post) {
        this.id = post.Id().toString();
        this.title = post.Title().toString();
        this.author = post.Author().toString();
        this.content = post.Content().toString();
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
}
