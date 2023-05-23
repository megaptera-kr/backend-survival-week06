package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.List;

public interface CommentDao {
    Comment saveComment(Comment comment);

    Comment findByCommentId(CommentId of);

    void deleteComment(CommentId id);

    List<Comment> findAllByPostId(PostId of);

    Comment updateComment(Comment comment);
}
