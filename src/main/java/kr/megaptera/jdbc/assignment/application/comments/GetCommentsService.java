package kr.megaptera.jdbc.assignment.application.comments;

import kr.megaptera.jdbc.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
    private final JdbcCommentDao jdbcCommentDao;

    @Autowired
    public GetCommentsService(
            JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public List<CommentReadDto> execute(String postId) {
        var entities = jdbcCommentDao.findByPostId(postId);
        var models = entities.stream().map(entity -> new Comment(entity)).toList();
        var dtos = models.stream().map(model -> new CommentReadDto(model)).toList();

        return dtos;
    }
}
