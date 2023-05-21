package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdatedDto;
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

    public void updateComment(String id, String postId,
                                    CommentUpdatedDto commentUpdatedDto) {
        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));

        comment.update(commentUpdatedDto.getContent());

        commentDao.save(comment);
    }
}
