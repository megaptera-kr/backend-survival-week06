package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;

import java.util.List;

public interface CommentDao {

    List<Comment> findByPostId(String postId);

    Comment find(CommentId id);

    void save(Comment comment);

    void update(Comment comment);

    void delete(CommentId id);

}
