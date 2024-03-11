package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll(PostId postId);

    Comment find(CommentId id, PostId postId);

    void save(Comment comment);

    void delete(CommentId id);
}
