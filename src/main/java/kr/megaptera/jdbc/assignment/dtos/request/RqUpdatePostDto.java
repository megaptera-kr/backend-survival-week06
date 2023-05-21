package kr.megaptera.jdbc.assignment.dtos.request;

public class RqUpdatePostDto {

    private String title;

    private String content;

    public RqUpdatePostDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
