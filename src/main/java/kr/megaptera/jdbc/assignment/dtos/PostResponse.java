package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Post;

public class PostResponse {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostResponse(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostResponse(Post post) {
        this(post.getId(), post.getTitle(), post.getAuthor(), post.getContent());
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
