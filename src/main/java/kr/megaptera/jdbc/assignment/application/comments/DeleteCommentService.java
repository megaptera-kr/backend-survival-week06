package kr.megaptera.jdbc.assignment.application.comments;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final JdbcCommentDao jdbcCommentDao;

    @Autowired
    public DeleteCommentService(
            JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public CommentReadDto execute(String commentId) throws CommentNotFoundException {
        var entity = jdbcCommentDao.find(commentId);
        if (entity == null) {
            throw new CommentNotFoundException();
        }

        jdbcCommentDao.remove(entity);

        var model = new Comment(entity);
        var dto = new CommentReadDto(model);

        return dto;
    }
}
