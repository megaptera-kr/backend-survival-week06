package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.dtos.request.RqUpdateCommentDto;
import kr.megaptera.jdbc.assignment.exceptions.NotFoundException;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.Content;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentDao commentDao;

    public UpdateCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public ResponseEntity<CommentDto> updateComment(RqUpdateCommentDto dto, int postId, int commentId) {
        Comment comment = commentDao.findByCommentId(CommentId.of(commentId));
        if (!comment.isEqualPostId(PostId.of(postId))) {
            throw new NotFoundException();
        }
        comment.update(Content.of(dto.getContent()));
        Comment updateComment = commentDao.updateComment(comment);
        CommentDto commentDto = new CommentDto(updateComment);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
}
