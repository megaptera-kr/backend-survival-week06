package kr.megaptera.jdbc.assignment.domains.model;

import java.util.Objects;

public class PostAuthor {

    private String author;

    public PostAuthor(String author) {
        this.author = author;
    }

    public static PostAuthor of(String author) {
        return new PostAuthor(author);
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostAuthor that)) return false;
        return Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }
}
