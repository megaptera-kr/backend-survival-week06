package kr.megaptera.jdbc.assignment.dtos;

public class CommentDto {
    private String postId;
    private String id;
    private String content;
    private String author;

    public CommentDto() {
    }

    public CommentDto(String postId, String id, String content, String author) {
        this.postId = postId;
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public CommentDto(String postId) {
        this.postId = postId;
    }

    public CommentDto(String id, String postId) {
        this.id = id;
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
