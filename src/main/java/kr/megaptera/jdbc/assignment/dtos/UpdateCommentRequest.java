package kr.megaptera.jdbc.assignment.dtos;

public class UpdateCommentRequest {
    private String content;

    public UpdateCommentRequest() {
    }

    public UpdateCommentRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
