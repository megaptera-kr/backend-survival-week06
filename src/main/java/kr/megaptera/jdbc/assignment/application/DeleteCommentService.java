package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {

    private final JdbcCommentDao commentDao;

    public DeleteCommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto deleteComment(String id, String postId) {
        CommentDto commentDto = commentDao.find(id, postId);
        if (commentDto == null)
            throw new CommentNotFound();
        commentDao.delete(id, postId);
        return commentDto;
    }
}
