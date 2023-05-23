package kr.megaptera.jdbc.assignment.model;

import kr.megaptera.jdbc.assignment.domains.model.MultilineText;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MultilineTextTest {

    @Test
    void Creation() {
        MultilineText text = new MultilineText("안녕하세요~\n다들 반갑습니다^^\n\n다들 식사는 하셨나요?\n");
        assertThat(text).isNotNull();
        assertThat(text).hasFieldOrProperty("content");
        assertThat(text.getContent().size()).isEqualTo(4);
    }

    @Test
    void testToString() {
        String start = "안녕하세요~\n다들 반갑습니다^^\n\n다들 식사는 하셨나요?";
        MultilineText text = new MultilineText(start);
        assertThat(text).isNotNull();

        String textString = text.toString();
        assertThat(textString).isEqualTo(start.trim());
    }
}