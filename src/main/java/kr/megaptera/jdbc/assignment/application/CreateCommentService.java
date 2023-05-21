package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentContent;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final CommentDao commentDao;

    public CreateCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void createComment(CommentDto commentDto, String postId) {
        Comment comment = new Comment(commentDto.getAuthor(), CommentContent.of(commentDto.getContent()));
        commentDao.save(comment, PostId.of(postId));
    }
}
