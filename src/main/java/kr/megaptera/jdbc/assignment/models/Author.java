package kr.megaptera.jdbc.assignment.models;

import java.util.Objects;

public class Author {
    private String author;

    public Author(String author) {
        this.author = author;
    }

    public static Author of(String author) {
        return new Author(author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author that = (Author) o;
        return Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }

    @Override
    public String toString() {
        return author;
    }
}
