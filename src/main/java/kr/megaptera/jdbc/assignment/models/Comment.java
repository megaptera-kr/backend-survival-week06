package kr.megaptera.jdbc.assignment.models;

import java.util.Objects;

public class Comment {
    private CommentId id;
    private String author;
    private CommentContent content;
    private PostId postId;

    public Comment(CommentId id, String author, CommentContent content, PostId postId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.postId = postId;
    }

    public Comment(String author, CommentContent content) {
        this.id = CommentId.generate();
        this.author = author;
        this.content = content;
    }

    public Comment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(author, comment.author) && Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content=" + content +
                ", postId=" + postId +
                '}';
    }

    public CommentId id() {
        return id;
    }

    public String author() {
        return author;
    }

    public CommentContent content() {
        return content;
    }

    public void update(String text) {
        this.content = CommentContent.of(text);
    }
}
