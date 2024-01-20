package kr.megaptera.jdbc.assignment.models;

import java.util.List;

public class PostContent {
    private List<String> content;

    private PostContent(List<String> content) {
        this.content = content;
    }

    public static PostContent of(String... content) {
        return new PostContent(List.of(content));
    }

    @Override
    public String toString() {
        return String.join("\n", content);
    }
}
