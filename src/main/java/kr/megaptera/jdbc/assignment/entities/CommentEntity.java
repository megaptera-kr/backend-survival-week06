package kr.megaptera.jdbc.assignment.entities;

import kr.megaptera.jdbc.assignment.utils.NewIdGenerator;

public class CommentEntity {
  private String id;
  private String postId;
  private String author;
  private String content;

  public CommentEntity() {
  }

  public CommentEntity(String id, String postId, String author, String content) {
    this.id = id;
    this.postId = postId;
    this.author = author;
    this.content = content;
  }

  public CommentEntity(String postId, String author, String content) {
    this.id = NewIdGenerator.getNewCommentId();
    this.postId = postId;
    this.author = author;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public String getPostId() {
    return postId;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }

  public void update(String content) {
    this.content = content;
  }
}
