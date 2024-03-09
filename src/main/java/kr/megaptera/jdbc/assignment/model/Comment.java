package kr.megaptera.jdbc.assignment.model;

public record Comment(
        CommentId id,
        PostId postId,
        String author,
        String content
) {

    public Comment(PostId postId, String author, String content) {
        this(CommentId.generate(), postId, author, content);
    }
}
