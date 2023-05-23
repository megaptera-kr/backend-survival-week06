package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.domains.dto.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.domains.model.Comment;
import kr.megaptera.jdbc.assignment.domains.model.CommentId;
import kr.megaptera.jdbc.assignment.domains.model.PostId;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final JdbcCommentDao commentDao;

    public UpdateCommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto updateComment(String id, String postId, CommentUpdateDto commentUpdateDto) {
        CommentDto commentDto = commentDao.find(id, postId);
        if (commentDto == null)
            throw new CommentNotFound();
        Comment comment = new Comment(CommentId.of(id), PostId.of(postId), commentDto);
        comment.updateComment(commentUpdateDto);
        commentDao.update(id, postId, comment);
        return new CommentDto(comment);
    }
}
