package kr.megaptera.jdbc.assignment.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultilineTextTest {
    @Test
    void creation() {
        MultilineText multilineText = new MultilineText("1\n2\n3");

        assertEquals("1\n2\n3", multilineText.toString());
//        assertThat(multilineText.toString()).isEqualTo("1\n2\n3");
    }
}