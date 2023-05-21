package kr.megaptera.jdbc.assignment.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MultilineText {
    private List<String> lines;

    public MultilineText(String text) {
        this.lines = Arrays.asList(text.split("\n"));
    }

    public static MultilineText of(String text) {
        return new MultilineText(text);
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }

    @Override
    public boolean equals(Object other) {
        MultilineText multilineText = (MultilineText) other;

        return Objects.equals(lines, multilineText.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }
}
