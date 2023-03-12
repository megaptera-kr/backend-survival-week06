package kr.megaptera.jdbc.assignment.models;

public class Comment {
    private CommentId id;
    private PostId postId;
    private String author;
    private MultilineText content;


    public Comment(CommentId id, PostId postId, String author, MultilineText content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, String author, MultilineText content) {
        this.id = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public void update(MultilineText content) {
        this.content = content;
    }


    public CommentId id() {
        return id;
    }

    public PostId postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }
}
