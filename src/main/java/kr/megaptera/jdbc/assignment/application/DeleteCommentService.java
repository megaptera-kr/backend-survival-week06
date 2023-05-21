package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final JdbcCommentDao jdbcCommentDao;

    public DeleteCommentService(JdbcCommentDao commentDao) {
        this.jdbcCommentDao = commentDao;
    }

    public void deleteComment(String id, String postId) {

        Comment comment = jdbcCommentDao.find(CommentId.of(id), PostId.of(postId));

        jdbcCommentDao.delete(comment.id());

    }
}
