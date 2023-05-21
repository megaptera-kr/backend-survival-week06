package kr.megaptera.jdbc.assignment.domains.model;

public class CommentAuthor {

    private String author;

    public CommentAuthor(String author) {
        this.author = author;
    }

    public static CommentAuthor of(String author) {
        return new CommentAuthor(author);
    }

    public String getAuthor() {
        return author;
    }
}
