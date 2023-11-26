package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;

import java.util.List;

public interface CommentDao {

    List<Comment> findAll(String postId);

    Comment find(CommentId commentId);

    void save(Comment comment);

    void delete(CommentId commentId);

    void update(Comment comment);
}
