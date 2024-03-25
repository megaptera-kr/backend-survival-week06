package kr.megaptera.jdbc.assignment.model;

import kr.megaptera.jdbc.assignment.models.MultilineText;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MultilineTextTest {
    @Test
    void creation() {
        MultilineText multilineText = new MultilineText("1/n2/n3");

        assertEquals("1/n2/n3", multilineText.toString());
    }
}
