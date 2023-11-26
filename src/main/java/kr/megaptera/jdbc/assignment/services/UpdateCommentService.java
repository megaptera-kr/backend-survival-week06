package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {


    private CommentDao commentDao;

    @Autowired
    public UpdateCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto updateCommentDto(String commentId, CommentDto commentDto) {
        Comment comment = commentDao.find(CommentId.of(commentId));
        if (comment == null) {
            throw new PostNotFound();
        }

        comment.update(MultilineText.of(commentDto.getContent()));
        commentDao.update(comment);
        return new CommentDto(comment);
    }
}
