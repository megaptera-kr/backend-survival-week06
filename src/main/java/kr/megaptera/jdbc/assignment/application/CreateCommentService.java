package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentResponse;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final CommentDao commentDao;

    public CreateCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentResponse createComment(Comment comment) {
        Comment saved = commentDao.save(comment);

        return new CommentResponse(saved);
    }

}
