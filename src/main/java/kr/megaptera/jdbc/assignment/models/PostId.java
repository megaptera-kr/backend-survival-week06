package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

import java.util.Objects;

public class PostId {
    public String value;

    public PostId(String value) {
        this.value = value;
    }

    public static PostId of(String value) {
        return new PostId(value);
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        PostId otherPostId = (PostId) other;

        return Objects.equals(value, otherPostId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
