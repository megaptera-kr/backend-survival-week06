package kr.megaptera.jdbc.assignment.daos;

import java.util.List;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;

public interface CommentDao {
  List<Comment> getCommentList(PostId postId);

  Comment findById(CommentId commentId);
  void save(PostId postId, Comment newComment);
  void update(Comment comment);
  void delete(Comment comment);
}
