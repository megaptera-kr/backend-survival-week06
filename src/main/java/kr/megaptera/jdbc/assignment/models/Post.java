package kr.megaptera.jdbc.assignment.models;

public class Post {

    private PostId id;

    private Title title;

    private Author author;

    private Content content;

    public Post(Title title, Author author, Content content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;
    }

    public Post(PostId id, Title title, Author author, Content content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId Id() {
        return id;
    }

    public Title Title() {
        return title;
    }

    public Author Author() {
        return author;
    }

    public Content Content() {
        return content;
    }

    public boolean isEqualId(PostId postId) {
        return this.id.equals(postId);
    }

    public void createId(PostId postId) {
        this.id = postId;
    }
}
