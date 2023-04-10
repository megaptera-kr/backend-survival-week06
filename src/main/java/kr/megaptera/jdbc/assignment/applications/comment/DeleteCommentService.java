package kr.megaptera.jdbc.assignment.applications.comment;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.models.comment.*;
import org.springframework.stereotype.*;

@Service
public class DeleteCommentService {

    private final JdbcCommentDao jdbcCommentDao;

    public DeleteCommentService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public void delete(String id, String postId) {
        Comment comment = jdbcCommentDao.find(id, postId);

        jdbcCommentDao.delete(comment);

    }
}
