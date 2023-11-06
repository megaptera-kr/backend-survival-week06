package kr.megaptera.jdbc.assignment.models.posts;

import java.util.Objects;

public class PostId {
    private String value;

    public PostId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
