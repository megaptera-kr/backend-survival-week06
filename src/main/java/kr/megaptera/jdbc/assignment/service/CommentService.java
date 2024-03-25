package kr.megaptera.jdbc.assignment.service;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final JdbcCommentDao commentDao;

    public CommentService(JdbcCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentDto> getList(CommentDto commentDto) {
        return commentDao.getList(commentDto);
    }

    public void insertComment(CommentDto commentDto) {
        commentDao.insertComment(commentDto);
    }

    public void updateComment(CommentDto commentDto) {
        commentDao.updateComment(commentDto);
    }

    public void deleteComment(CommentDto commentDto) {
        commentDao.deleteComment(commentDto);
    }
}
