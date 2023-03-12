package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final JdbcCommentDao jdbcCommentDao;

    public DeleteCommentService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public CommentDto deleteComment(String id) {

        Comment comment = jdbcCommentDao.find(CommentId.of(id));
        jdbcCommentDao.delete(CommentId.of(id));

        return new CommentDto(
                comment.id().toString(),
                comment.author(),
                comment.content().toString()
        );

    }

}
