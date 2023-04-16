package kr.megaptera.jdbc.assignment.domains;

public class Comment {
    private CommentId id;
    private String author;
    private String content;
    private PostId postId;

    public Comment() {
    }


    public Comment(CommentId id, String author, String content, PostId postId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.postId = postId;
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

    public String content() {
        return content;
    }

    public Comment(PostId postId, String author, String content) {
        this.id = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }


    public void update(String content) {
        this.content = content;
    }
}
