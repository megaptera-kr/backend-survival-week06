package kr.megaptera.jdbc.assignment.repository;

import kr.megaptera.jdbc.assignment.daos.CommentDAO;
import kr.megaptera.jdbc.assignment.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    private final CommentDAO commentDAO;

    public CommentRepository(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> getComments(String postId) {
        return commentDAO.findComments(postId);
    }

    public void createComment(Comment comment) {
        commentDAO.saveComment(comment);
    }

    public void updateComment(String commentId, String postId, String content) {
        commentDAO.updateComment(postId, commentId, content);
    }

    public void deleteComment(String commentId, String postId) {
        commentDAO.deleteComment(postId, commentId);
    }
}
