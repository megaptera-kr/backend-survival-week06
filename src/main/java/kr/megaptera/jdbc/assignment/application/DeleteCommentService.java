package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final JdbcCommentDao commentDao;

    public void deleteComment(String id, String postId) {

        Comment comment = commentDao.find(CommentId.of(id), PostId.of(postId));
        commentDao.delete(comment.id());

    }
}
