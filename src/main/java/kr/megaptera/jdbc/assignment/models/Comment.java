package kr.megaptera.jdbc.assignment.models;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;

public class Comment {

  CommentId id;
  String author;
  String content;

  public Comment(CommentId id, String author, String content) {
    this.id = id;
    this.author = author;
    this.content = content;
  }

  public CommentId id() {
    return id;
  }

  public String author() {
    return author;
  }

  public String content() {
    return content;
  }

  public Comment(String author, String content) {
    this.id = CommentId.generate();
    this.author = author;
    this.content = content;
  }

  public CommentDto toDto() {
    return new CommentDto(this.id.toString(), this.author, this.content);
  }

  public void update(String content) {
    this.content = content;
  }
}
