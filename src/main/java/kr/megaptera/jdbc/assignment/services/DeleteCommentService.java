package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private CommentDao commentDao;

    @Autowired
    public DeleteCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto delete(String commentId) {
        Comment comment = commentDao.find(CommentId.of(commentId));
        if (comment == null) {
            throw new PostNotFound();
        }
        commentDao.delete(comment.id());
        return new CommentDto(comment);
    }
}
