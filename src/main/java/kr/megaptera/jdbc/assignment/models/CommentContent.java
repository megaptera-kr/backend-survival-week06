package kr.megaptera.jdbc.assignment.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommentContent {
    private List<String> text;

    public CommentContent(String content) {
        this.text = Arrays.asList(content.split("\n"));
    }

    public static CommentContent of(String content){
        return new CommentContent(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentContent that = (CommentContent) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return text.stream().collect(Collectors.joining("\n"));
    }
}
