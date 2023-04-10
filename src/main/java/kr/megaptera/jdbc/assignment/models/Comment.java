package kr.megaptera.jdbc.assignment.models;

public class Comment {
    private String id;
    private final String postId;
    private final String author;
    private String content;

    public Comment(String postId, String author, String content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(String id, String postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void update(String content) {
        this.content = content;
    }

    public void generateId(Long id) {
        this.id = id.toString();
    }
}
