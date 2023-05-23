package kr.megaptera.jdbc.assignment.models;

public class Content {

    private String content;

    public Content(String content) {
        this.content = content;
    }

    public static Content of (String content) {
        return new Content(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
