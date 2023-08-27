package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final JdbcCommentDao jdbcCommentDao;

    public CreateCommentService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public void createComment(String postId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent()
        );

        jdbcCommentDao.save(comment);
    }
}
