package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.*;

import java.util.*;

public interface CommentDao {
    List<Comment> findAll(PostId postId);

    Comment find(CommentId id, PostId postId);

    void save(Comment comment);

    void delete(CommentId id);
}
