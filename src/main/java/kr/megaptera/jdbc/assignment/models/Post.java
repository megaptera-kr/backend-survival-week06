package kr.megaptera.jdbc.assignment.models;

import java.util.Objects;

public class Post {
    private PostId id;
    private PostTitle title;

    private String author;
    private PostContent content;

    public Post(PostId id, PostTitle title, String author, PostContent content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(PostTitle title, String author, PostContent content) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(author, post.author) && Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, content);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title=" + title +
                ", author='" + author + '\'' +
                ", content=" + content +
                '}';
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

    public PostContent content() {
        return content;
    }
}
