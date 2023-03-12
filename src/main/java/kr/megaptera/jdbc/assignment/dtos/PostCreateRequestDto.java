package kr.megaptera.jdbc.assignment.dtos;

public class PostCreateRequestDto {
  private String title;
  private String author;
  private String content;

  public PostCreateRequestDto() {
  }

  public PostCreateRequestDto(String title, String author, String content) {
    this.title = title;
    this.author = author;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }
}
