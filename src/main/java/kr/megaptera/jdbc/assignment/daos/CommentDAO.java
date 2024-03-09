package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.model.Comment;

import java.util.List;

public interface CommentDAO {
    void saveComment(Comment comment);

    void updateComment(String postId, String commentId, String content);

    void deleteComment(String postId, String commentId);

    List<Comment> findComments(String postId);
}
