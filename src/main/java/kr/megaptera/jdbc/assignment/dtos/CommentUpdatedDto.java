package kr.megaptera.assignment.dtos;

public class CommentUpdatedDto {
    private String content;

    public CommentUpdatedDto() {
    }

    public CommentUpdatedDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
