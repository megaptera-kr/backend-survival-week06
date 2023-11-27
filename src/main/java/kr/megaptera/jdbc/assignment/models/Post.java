package kr.megaptera.jdbc.assignment.models;

import java.util.Objects;

public class Post {
    private PostId id;
    private PostTitle title;
    private PostAuthor author;
    private MultilineText content;

    public Post(PostTitle title, PostAuthor author, MultilineText content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(PostId id, PostTitle title, PostAuthor author, MultilineText content) {
        this.id = id;
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

    public PostAuthor author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    public void update(PostTitle title, MultilineText content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +
                ", content=" + content +
                '}';
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
}
