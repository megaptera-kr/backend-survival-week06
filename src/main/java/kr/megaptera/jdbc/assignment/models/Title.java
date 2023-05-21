package kr.megaptera.jdbc.assignment.models;

public class Title {

    private String title;

    public Title(String title) {
        this.title = title;
    }

    public static Title of (String title) {
        return new Title(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
