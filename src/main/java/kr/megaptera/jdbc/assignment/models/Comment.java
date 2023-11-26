package kr.megaptera.jdbc.assignment.models;

public class Comment {
    private PostId postId;
    private CommentId id;
    private CommentAuthor author;
    private MultilineText content;

    public Comment(CommentId id, PostId postId, CommentAuthor author, MultilineText content) {
        this.postId = postId;
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, CommentAuthor author, MultilineText content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public PostId postId() {
        return postId;
    }

    public CommentId id() {
        return id;
    }

    public CommentAuthor author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    public void update(MultilineText content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", id=" + id +
                ", author=" + author +
                ", content=" + content +
                '}';
    }
}
