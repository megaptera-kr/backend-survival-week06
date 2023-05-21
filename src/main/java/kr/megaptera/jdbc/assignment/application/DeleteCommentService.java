package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentDao commentDao;

    public DeleteCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void delete(String id, String postId) {
        System.out.println("deleteservice :" + id + "|" + postId);
        commentDao.delete(id, PostId.of(postId));
    }
}
