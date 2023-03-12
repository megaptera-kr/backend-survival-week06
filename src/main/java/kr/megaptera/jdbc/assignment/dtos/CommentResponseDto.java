package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.entities.CommentEntity;

public class CommentResponseDto {
  private String id;
  private String author;
  private String content;

  public String getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }

  public CommentResponseDto() {
  }

  public static CommentResponseDto of(CommentEntity commentEntity) {
    return new CommentResponseDto(commentEntity.getId(), commentEntity.getAuthor(),
      commentEntity.getContent());
  }

  public CommentResponseDto(String id, String author, String content) {
    this.id = id;
    this.author = author;
    this.content = content;
  }
}
