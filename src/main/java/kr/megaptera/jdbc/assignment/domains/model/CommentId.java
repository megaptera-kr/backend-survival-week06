package kr.megaptera.jdbc.assignment.domains.model;

import com.github.f4b6a3.tsid.TsidCreator;

import java.util.Objects;

public class CommentId {

    private String id;

    public CommentId() {
        this.id = generate();
    }

    public CommentId(String id) {
        this.id = id;
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    public String getId() {
        return id;
    }

    public String generate() {
        return TsidCreator.getTsid().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentId commentId)) return false;
        return Objects.equals(id, commentId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
