package kr.megaptera.jdbc.assignment.dtos;

public class CommentUpdateRequestDto {
  private String content;

  public CommentUpdateRequestDto() {
  }

  public CommentUpdateRequestDto(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
