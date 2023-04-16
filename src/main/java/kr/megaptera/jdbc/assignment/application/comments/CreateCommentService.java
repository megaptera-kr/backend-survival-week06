package kr.megaptera.jdbc.assignment.application.comments;

import kr.megaptera.jdbc.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final JdbcCommentDao jdbcCommentDao;

    @Autowired
    public CreateCommentService(
            JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public CommentReadDto execute(String postId, CommentCreateDto commentCreateDto) {
        var model = new Comment(postId, commentCreateDto);
        var entity = new CommentEntity(model);

        jdbcCommentDao.add(entity);

        var commentReadDto = new CommentReadDto(model);
        return commentReadDto;
    }
}
