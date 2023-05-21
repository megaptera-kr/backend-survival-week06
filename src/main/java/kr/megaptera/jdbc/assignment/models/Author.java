package kr.megaptera.jdbc.assignment.models;

public class Author {

    private String author;

    public Author(String author) {
        this.author = author;
    }

    public static Author of (String author) {
        return new Author(author);
    }

    @Override
    public String toString() {
        return author;
    }
}
