package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final JdbcCommentDao jdbcCommentDao;

    public UpdateCommentService(JdbcCommentDao commentDao) {
        this.jdbcCommentDao = commentDao;
    }

    public CommentDto updateComment(String id, String postId, CommentUpdateDto commentUpdateDto) {
        Comment comment = jdbcCommentDao.find(CommentId.of(id), PostId.of(postId));
        comment.update(commentUpdateDto.getContent());

        return new CommentDto(comment);
    }
}
