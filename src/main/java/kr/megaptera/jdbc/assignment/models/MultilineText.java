package kr.megaptera.jdbc.assignment.models;

import lombok.Data;

import java.util.Arrays;

@Data
public class MultilineText {
    private String[] lines;

    public MultilineText(String text) {
        appendText(text);
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        if (lines.length == 1) {
            stringBuilder.append(lines[0]);
        } else {
            Arrays.stream(lines).forEach(line -> stringBuilder.append(line + "\n"));
        }

        return stringBuilder.toString();
    }

    public void appendText(String text){
        lines = text.split("\n");
    }
}
