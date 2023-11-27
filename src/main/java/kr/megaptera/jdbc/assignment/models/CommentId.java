package kr.megaptera.jdbc.assignment.models;

public class CommentId extends Id {

    public CommentId(String id) {
        super(id);
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    public static CommentId of(int id) {
        return new CommentId(String.valueOf(id));
    }
}
