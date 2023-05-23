package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class UpdateCommentService {

    private final JdbcCommentDao commentDao;

    public UpdateCommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void updateComment(String id, String postId,
                              CommentDto commentUpdatedDto) {
        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));

        comment.update(commentUpdatedDto.getContent());

        commentDao.save(comment);
    }
}
