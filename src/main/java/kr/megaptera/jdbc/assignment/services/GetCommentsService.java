package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {

    private CommentDao commentDao;

    @Autowired
    public GetCommentsService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentDto> getCommentDtos(String postId) {
        List<Comment> comments = commentDao.findAll(postId);
        return comments.stream().map(comment -> new CommentDto(comment)).toList();

    }
}
