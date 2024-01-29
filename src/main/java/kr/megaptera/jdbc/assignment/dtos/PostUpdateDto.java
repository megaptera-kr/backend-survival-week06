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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostUpdateDto{" +
               "title='" + title + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
