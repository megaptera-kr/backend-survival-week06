package kr.megaptera.jdbc.assignment.applications.comment;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.comment.*;
import kr.megaptera.jdbc.assignment.models.comment.*;
import kr.megaptera.jdbc.assignment.models.post.*;
import org.springframework.stereotype.*;

@Service
public class CreateCommentService {

    private final JdbcCommentDao jdbcCommentDao;

    public CreateCommentService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public void create(String postId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent());
        jdbcCommentDao.save(comment);
    }
}
