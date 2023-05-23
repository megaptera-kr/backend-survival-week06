package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.*;

import java.util.*;

public interface CommentDao {
    List<Comment> findAll(PostId postId);

    void create(Comment comment);

    void update(Comment comment);

    void delete(String postId, String id);

    Comment find(CommentId commentId, PostId postId);
}
