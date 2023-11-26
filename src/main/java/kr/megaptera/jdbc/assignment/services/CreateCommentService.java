package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentAuthor;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final CommentDao commentDao;

    @Autowired
    public CreateCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto createCommentDto(String postId, CommentDto commentDto) {

        Comment comment = new Comment(PostId.of(postId), CommentAuthor.of(commentDto.getAuthor()), MultilineText.of(commentDto.getContent()));
        commentDao.save(comment);
        return new CommentDto(comment);
    }
}
