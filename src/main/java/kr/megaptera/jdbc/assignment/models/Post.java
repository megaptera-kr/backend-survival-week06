package kr.megaptera.jdbc.assignment.models;

public class Post {
    private PostId id;

    private PostTitle title;

    private String author;

    private MultilineText content;

    public Post(PostId id, PostTitle title, String author, MultilineText content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(PostTitle title, String author, MultilineText content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId id() {
        return id;
    }

    public PostTitle title() {
        return title;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    public void update(PostTitle title, MultilineText content) {
        this.title = title;
        this.content = content;
    }
}
