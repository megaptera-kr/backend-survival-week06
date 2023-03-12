package kr.megaptera.jdbc.assignment.application;


import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final JdbcCommentDao jdbcCommentDao;

    public UpdateCommentService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }


    public CommentDto updateComment(String id, CommentDto commentDto) {
        Comment comment = jdbcCommentDao.find(CommentId.of(id));
        comment.update(
                MultilineText.of(commentDto.getContent())
        );

        Comment newComment = jdbcCommentDao.find(CommentId.of(id));

        return new CommentDto(
                newComment.id().toString(),
                newComment.author(),
                newComment.content().toString()
        );
    }


}
