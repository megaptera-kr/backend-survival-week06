package kr.megaptera.jdbc.assignment.models;

import java.util.List;
import java.util.stream.Collectors;

public class CommentContent {
    private List<String> content;

    private CommentContent(List<String> content) {
        this.content = content;
    }

    public static CommentContent of(String... content) {
        return new CommentContent(List.of(content));
    }

    @Override
    public String toString() {
        return String.join("\n", content);
    }
}
