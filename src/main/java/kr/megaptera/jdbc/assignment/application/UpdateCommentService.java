package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final CommentDao commentDao;

    public UpdateCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void update(String id, CommentDto commentDto, String postId) {
        Comment comment = commentDao.findComment(id, PostId.of(postId));
        System.out.println("updateservice : find" + comment);
        comment.update(commentDto.getContent());
        System.out.println("update after :" + comment);
        commentDao.update(id, comment, PostId.of(postId));
    }
}
