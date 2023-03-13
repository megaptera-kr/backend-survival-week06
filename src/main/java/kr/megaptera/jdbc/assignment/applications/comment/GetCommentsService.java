package kr.megaptera.jdbc.assignment.applications.comment;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.comment.*;
import kr.megaptera.jdbc.assignment.models.comment.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GetCommentsService {

    private final JdbcCommentDao jdbcCommentDao;

    public GetCommentsService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public List<CommentDto> getCommentDtos(String postId) {
        List<Comment> comments = jdbcCommentDao.findAll(postId);
        return comments.stream().map(CommentDto::new).toList();
    }
}
