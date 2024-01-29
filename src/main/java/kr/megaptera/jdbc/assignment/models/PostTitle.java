package kr.megaptera.jdbc.assignment.models;

public class PostTitle {
    private String title;

    private PostTitle(String title) {
        this.title = title;
    }

    public static PostTitle of(String title) {
        return new PostTitle(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
