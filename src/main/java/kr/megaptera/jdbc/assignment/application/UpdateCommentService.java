package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final JdbcCommentDao commentDao;

    public UpdateCommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void updateComment(String id, String postId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));

        comment.update(commentUpdateDto.getContent());

        commentDao.save(comment);
    }
}
