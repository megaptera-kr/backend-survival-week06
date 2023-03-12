package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository implements CommentDao {
  Map<String, CommentEntity> comments;

  public CommentRepository() {
    this.comments = new HashMap<>();
  }

  public List<CommentEntity> findAll(String postId) {
    List<CommentEntity> commentList = comments.values().stream()
                                              .filter(comment -> comment.getPostId().equals(postId))
                                              .toList();

    return commentList;
  }

  public CommentEntity find(String id, String postId) {
    CommentEntity comment = comments.get(id);

    if (comment == null || !comment.getPostId().equals(postId)) {
      throw new CommentNotFound();
    }

    return comment;
  }

  public void save(CommentEntity comment) {
    comments.put(comment.getId(), comment);
  }

  public void delete(String commentId) {
    comments.remove(commentId);
  }
}
