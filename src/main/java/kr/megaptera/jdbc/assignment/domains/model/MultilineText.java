package kr.megaptera.jdbc.assignment.domains.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MultilineText {

    private final List<String> content;

    public MultilineText(String text) {
        // StringTokenizer로 나누면 빈칸을 버려버리는듯
        this.content = new ArrayList<>(
                Arrays.stream(text.split("\n"))
                        .toList());
    }

    public static MultilineText of(String content) {
        return new MultilineText(content);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (String line : content) {
            sb.append(line).append("\n");
        }

        return sb.toString().trim();
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultilineText that)) return false;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
