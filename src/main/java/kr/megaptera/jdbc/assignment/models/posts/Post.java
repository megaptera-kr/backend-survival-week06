package kr.megaptera.jdbc.assignment.models.posts;

public class Post {
    PostId id;
    String title;
    String author;
    String content;

    public Post(PostId id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

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

    public void setId(PostId id) {
        this.id = id;
    }
}
