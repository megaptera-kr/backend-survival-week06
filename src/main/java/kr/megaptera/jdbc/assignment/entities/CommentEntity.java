package kr.megaptera.jdbc.assignment.entities;

import kr.megaptera.jdbc.assignment.models.Author;
import kr.megaptera.jdbc.assignment.models.CommentContent;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;

public class CommentEntity {
    private CommentId id;
    private PostId postId;
    private Author author;
    private CommentContent content;

    private CommentEntity(CommentId id, PostId postId, Author author, CommentContent content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public static CommentEntity generate(PostId postId, Author author, CommentContent content) {
        return new CommentEntity(CommentId.generate(), postId, author, content);
    }

    public static CommentEntity of(CommentId id, PostId postId, Author author, CommentContent content) {
        return new CommentEntity(id, postId, author, content);
    }

    public CommentId getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public CommentContent getContent() {
        return content;
    }

    public PostId getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
               "id=" + id +
               ", author=" + author +
               ", content=" + content +
               '}';
    }

    public void update(CommentContent content) {
        this.content = content;
    }
}
