package kr.megaptera.jdbc.assignment.dtos;

public class PostUpdateDto {
    private String title;
    private String content;

    public PostUpdateDto() {
    }

    public PostUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
