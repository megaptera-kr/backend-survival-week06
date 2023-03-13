package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.comment.*;

import java.util.*;

public interface CommentDao {

    List<Comment> findAll(String postId);

    void save(Comment comment);

    Comment find(String id, String postId);

    void delete(Comment comment);

}
