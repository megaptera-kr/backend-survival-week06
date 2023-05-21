package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {

    private final JdbcCommentDao commentDao;

    public GetCommentsService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentDto> getComments(String postId) {
        return commentDao.findByPost(postId);
    }
}
