package kr.megaptera.jdbc.assignment.models.post;

import java.util.*;

public class MultilineText {

    private List<String> multilineText;

    public MultilineText(String multilineText) {
        this.multilineText = Arrays.stream(multilineText.split("\n")).toList();
    }

    public static MultilineText of(String multilineText) {
        return new MultilineText(multilineText);
    }

    @Override
    public String toString() {
        return String.join("\n", multilineText);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultilineText that = (MultilineText) o;
        return Objects.equals(multilineText, that.multilineText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(multilineText);
    }
}
