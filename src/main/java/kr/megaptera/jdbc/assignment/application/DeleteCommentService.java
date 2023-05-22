package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.springframework.stereotype.*;

@Service
public class DeleteCommentService {
    private final JdbcCommentDao commentDao;

    public DeleteCommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }


    public void deleteComment(String id, String postId) {
        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));

        commentDao.delete(comment.id());
    }

}
