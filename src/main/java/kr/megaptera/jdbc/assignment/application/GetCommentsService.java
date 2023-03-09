package kr.megaptera.jdbc.assignment.application;

import java.util.List;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentResponse;
import kr.megaptera.jdbc.assignment.models.Comment;
import org.springframework.stereotype.Service;

@Service
public class GetCommentsService {
    private final CommentDao commentDao;

    public GetCommentsService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentResponse> getComments(String postId) {
        List<Comment> comments = commentDao.findByPostId(postId);

        return comments.stream().map(CommentResponse::new).toList();
    }
}
