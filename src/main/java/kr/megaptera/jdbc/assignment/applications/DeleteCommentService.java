package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final JdbcCommentDao jdbcCommentDao;

    public DeleteCommentService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public void deleteComment(String id, String postId) {
        Comment comment = jdbcCommentDao.find(CommentId.of(id), PostId.of(postId));

        jdbcCommentDao.delete(comment.id());
    }
}
