package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentDao commentDao;

    public DeleteCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void deleteComment(String id, String postId) {
        commentDao.delete(id, postId);
    }
}
