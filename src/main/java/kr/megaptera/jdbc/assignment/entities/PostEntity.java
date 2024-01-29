package kr.megaptera.jdbc.assignment.entities;

import kr.megaptera.jdbc.assignment.models.Author;
import kr.megaptera.jdbc.assignment.models.PostContent;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;

public class PostEntity {
    private PostId id;
    private Author author;
    private PostTitle title;
    private PostContent content;

    private PostEntity(PostId id, Author author, PostTitle title, PostContent content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public static PostEntity of(PostId id, Author author, PostTitle title, PostContent content) {
        return new PostEntity(id, author, title, content);
    }

    public static PostEntity createNew(Author author, PostTitle title, PostContent content) {
        return new PostEntity(PostId.generate(), author, title, content);
    }

    public PostId getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public PostTitle getTitle() {
        return title;
    }

    public PostContent getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
               "id='" + id + '\'' +
               ", author='" + author + '\'' +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               '}';
    }

    public void update(PostTitle title, PostContent content) {
        this.title = title;
        this.content = content;
    }
}
