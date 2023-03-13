package kr.megaptera.jdbc.assignment.models.comment;

import com.github.f4b6a3.tsid.*;

import java.util.*;

public class CommentId {
    private String id;

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    public CommentId(String id) {
        this.id = id;
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return Objects.equals(id, commentId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
