package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.NotFoundException;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {

    private final CommentDao commentDao;

    public DeleteCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public ResponseEntity<CommentDto> deleteComment(int postId, int commentId) {
        Comment comment = commentDao.findByCommentId(CommentId.of(commentId));
        if (!comment.isEqualPostId(PostId.of(postId))) {
            throw new NotFoundException();
        }
        commentDao.deleteComment(comment.Id());
        CommentDto commentDto = new CommentDto(comment);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
}
