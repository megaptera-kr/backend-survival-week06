package kr.megaptera.jdbc.assignment.models;

public class CommentAuthor extends Author {

    public CommentAuthor(String author) {
        super(author);
    }

    public static CommentAuthor of(String author) {
        return new CommentAuthor(author);
    }
}
