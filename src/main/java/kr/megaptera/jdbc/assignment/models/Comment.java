package kr.megaptera.jdbc.assignment.models;

public class Comment {

    private CommentId id;

    private PostId postId;

    private Author author;

    private Content content;

    public Comment(PostId postId, Author author, Content content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public void update(Content content) {
        this.content = content;
    }

    public CommentId Id() {
        return id;
    }

    public PostId PostId() {
        return postId;
    }

    public Author Author() {
        return author;
    }

    public Content Content() {
        return content;
    }

    public boolean isEqualId(CommentId commentId) {
        return this.id.equals(commentId);
    }

    public boolean isEqualPostId(PostId postId) {
        return this.postId.equals(postId);
    }

    public void createId(CommentId commentId) {
        this.id = commentId;
    }
}
