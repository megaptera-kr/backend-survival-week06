package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.domains.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CommentService {

    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentDto> getList(String postId) {
        List<Comment> comments = commentDao.findAll(PostId.of(postId));

        return comments.stream().map(CommentDto::new).toList();
    }

    public void createComment(String postId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(PostId.of(postId), commentCreateDto.getAuthor(), commentCreateDto.getContent());
        commentDao.create(comment);
    }

    public void updateComment(String postId, CommentUpdateDto commentupdateDto, String id) {
        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));
        comment.update(commentupdateDto.getContent());
        commentDao.update(comment);
    }

    public void deleteComment(String postId, String id) {
        commentDao.delete(postId, id);
    }
}
