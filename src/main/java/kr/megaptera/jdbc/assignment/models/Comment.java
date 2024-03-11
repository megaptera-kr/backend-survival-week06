package kr.megaptera.jdbc.assignment.models;

import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;

public class Comment {
    private CommentId id;
    private PostId postId;
    private String author;
    private String content;

    public Comment(CommentId id, PostId postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, String author, String content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

    public CommentId id() {
        return  id;
    }

    public PostId postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }
}
