package kr.megaptera.jdbc.assignment.models;

public class PostId extends Id {

    public PostId(String id) {
        super(id);
    }

    public static PostId of(String id) {
        return new PostId(id);
    }

    public static PostId of(int id) {
        return new PostId(String.valueOf(id));
    }
}
