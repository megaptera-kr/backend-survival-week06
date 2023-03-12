package kr.megaptera.jdbc.assignment.utils;

public class NewIdGenerator {
  private static String newCommentId = "0";
  private static String newPostId = "0";

  public static String getNewCommentId() {
    String result = newCommentId;
    newCommentId = "" + (Integer.valueOf(newCommentId) + 1);
    return result;
  }

  public static String getNewPostId() {
    String result = newPostId;
    newPostId = "" + (Integer.valueOf(newPostId) + 1);
    return result;
  }
}
