package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.stereotype.*;

@Service
public class CreateCommentService {
    private final JdbcCommentDao commentDao;

    public CreateCommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void create(String postId,
                              CommentDto commentCreateDto) {
        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent()
        );

        commentDao.save(comment);
    }
}
