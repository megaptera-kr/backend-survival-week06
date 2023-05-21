package kr.megaptera.jdbc.assignment.models;

public class Post {
    private PostId id;

    private String title;

    private String author;

    private MultilineText content;

    public Post(PostId id, String title, String author, MultilineText content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, MultilineText content) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    public void update(String title, MultilineText content) {
        this.title = title;
        this.content = content;
    }
}
