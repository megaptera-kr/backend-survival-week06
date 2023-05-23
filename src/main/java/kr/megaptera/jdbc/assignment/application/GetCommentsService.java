package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

import java.util.List;

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
