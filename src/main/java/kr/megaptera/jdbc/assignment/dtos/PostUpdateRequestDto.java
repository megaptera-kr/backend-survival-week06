package kr.megaptera.jdbc.assignment.dtos;

public class PostUpdateRequestDto {
  private String title;
  private String content;


  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public PostUpdateRequestDto() {
  }

  public PostUpdateRequestDto(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
