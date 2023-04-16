package kr.megaptera.jdbc.assignment.domains;

import com.github.f4b6a3.tsid.*;

public class CommentId {
    String id;

    public CommentId() {
    }

    public CommentId(String id) {
        this.id = id;
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
