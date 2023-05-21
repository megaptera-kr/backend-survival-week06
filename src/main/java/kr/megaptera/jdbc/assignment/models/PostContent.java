package kr.megaptera.jdbc.assignment.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PostContent {
    private List<String> text;

    public PostContent(String content) {
        this.text = Arrays.asList(content.split("\n"));
    }

    public static PostContent of(String content){
        return new PostContent(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostContent that = (PostContent) o;
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
