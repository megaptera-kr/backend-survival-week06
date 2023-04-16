package kr.megaptera.jdbc.assignment.application.comments;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.jdbc.assignment.dtos.comments.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final JdbcCommentDao jdbcCommentDao;

    @Autowired
    public UpdateCommentService(
            JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public CommentReadDto execute(String commentId, String postId, CommentUpdateDto commentUpdateDto) throws CommentNotFoundException {
        var oldEntity = jdbcCommentDao.find(commentId);
        if(oldEntity == null){
            throw new CommentNotFoundException();
        }

        var model = new Comment(oldEntity);

        model.update(commentUpdateDto);

        var newEntity = new CommentEntity(model);

        jdbcCommentDao.update(newEntity);

        var commentReadDto = new CommentReadDto(model);

        return commentReadDto;
    }
}
