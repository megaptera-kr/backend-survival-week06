package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentDao commentDao;

    public DeleteCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto deleteComment(String id, String postId) {

        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));

        commentDao.delete(comment.id());

        return new CommentDto(comment);
    }
}
