package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll(PostId postId);

    void save(Comment comment, PostId postId);

    void update(String id, Comment comment, PostId postId);

    void delete(String id, PostId postId);

    Comment findComment(String id, PostId postId);
}
