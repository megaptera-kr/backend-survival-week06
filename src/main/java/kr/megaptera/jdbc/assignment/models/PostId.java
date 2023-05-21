package kr.megaptera.jdbc.assignment.models;

import java.util.Objects;

public class PostId {
    private String id;

    public PostId(String id) {
        this.id = id;
    }

    public static PostId of(int id) {
        return new PostId(Integer.toString(id));
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return Objects.equals(id, postId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
