package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GetCommentsService {
    private final JdbcCommentDao commentDao;

    public GetCommentsService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentDto> getCommentDtos(String postId) {
        List<Comment> comments = commentDao.findAll(PostId.of(postId));

        return comments.stream().map(CommentDto::new).toList();
    }
}
