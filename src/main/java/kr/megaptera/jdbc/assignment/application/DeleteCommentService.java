package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class DeleteCommentService {
    @Autowired
    private JdbcCommentDao commentDao;


    public void deleteComment(String id, String postId) {
        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));

        commentDao.delete(comment.id());
    }

}
