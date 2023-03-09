package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.UpdateCommentRequest;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentDao commentDao;

    public UpdateCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void updateComment(String id, String postId, UpdateCommentRequest request) {
        commentDao.update(id, postId, request.getContent());
    }
}
