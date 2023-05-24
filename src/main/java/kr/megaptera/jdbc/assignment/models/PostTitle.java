package kr.megaptera.jdbc.assignment.models;

import java.util.Objects;

public class PostTitle {
    private String title;

    public PostTitle(String title) {
        this.title = title;
    }

    public static PostTitle of(String title) {
        return new PostTitle(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTitle postTitle = (PostTitle) o;
        return Objects.equals(title, postTitle.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
