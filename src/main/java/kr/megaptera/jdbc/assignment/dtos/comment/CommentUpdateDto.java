package kr.megaptera.jdbc.assignment.dtos.comment;

public class CommentUpdateDto {
    private String content;

    public CommentUpdateDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
