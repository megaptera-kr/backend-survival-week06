package kr.megaptera.jdbc.assignment.domains;


public class Post {
    private PostId id;
    private String title;
    private String author;
    private String content;

    public PostId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Post() {
    }

    public Post(PostId id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, String content) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

}
