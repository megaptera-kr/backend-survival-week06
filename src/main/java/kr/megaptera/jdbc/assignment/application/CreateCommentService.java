package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final JdbcCommentDao commentDao;

    public CreateCommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDto createComment(String postId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent()
        );

        commentDao.save(comment);

        return new CommentDto(comment);
    }
}
