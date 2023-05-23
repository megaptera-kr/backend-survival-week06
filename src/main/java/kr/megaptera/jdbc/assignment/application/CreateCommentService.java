package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.domains.model.Comment;
import kr.megaptera.jdbc.assignment.domains.model.PostId;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final JdbcCommentDao commentDao;

    public CreateCommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto createComment(String postId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(PostId.of(postId), commentCreateDto);
        commentDao.save(postId, comment);
        return new CommentDto(comment);
    }
}
