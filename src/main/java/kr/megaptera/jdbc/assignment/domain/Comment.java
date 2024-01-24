package kr.megaptera.jdbc.assignment.domain;

import kr.megaptera.jdbc.assignment.utils.PostUtil;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
public class Comment {

    private String id;

    private String postId;

    private String author;

    private String content;

    public Comment(String id, String postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(author, comment.author) && Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content);
    }

    public void update(String content) {
        this.content = content;
    }
}
