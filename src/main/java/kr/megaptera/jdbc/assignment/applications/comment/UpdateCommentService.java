package kr.megaptera.jdbc.assignment.applications.comment;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.comment.*;
import kr.megaptera.jdbc.assignment.models.comment.*;
import org.springframework.stereotype.*;

@Service
public class UpdateCommentService {

    private final JdbcCommentDao jdbcCommentDao;

    public UpdateCommentService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public void updateComment(String id,
                              String postId,
                              CommentUpdateDto commentUpdateDto) {

        Comment comment = jdbcCommentDao.find(id, postId);

        comment.update(commentUpdateDto);

        jdbcCommentDao.save(comment);
    }
}
