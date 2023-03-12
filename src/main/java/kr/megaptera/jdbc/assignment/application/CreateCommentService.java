package kr.megaptera.jdbc.assignment.application;


import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final JdbcCommentDao jdbcCommentDao;

    public CreateCommentService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public CommentDto createComment(CommentDto commentDto, String postId) {

        Comment comment = new Comment(
                PostId.of(postId),
                commentDto.getAuthor(),
                MultilineText.of(commentDto.getContent())
        );

        jdbcCommentDao.save(comment);

        return new CommentDto(
                comment.id().toString(),
                comment.author(),
                comment.content().toString()
        );

    }

}
