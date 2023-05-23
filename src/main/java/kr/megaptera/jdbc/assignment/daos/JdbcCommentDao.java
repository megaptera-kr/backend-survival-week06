package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCommentDao implements CommentDao {

    @Override
    public Comment saveComment(Comment comment) {
        return null;
    }

    @Override
    public Comment findByCommentId(CommentId of) {
        return null;
    }

    @Override
    public void deleteComment(CommentId id) {

    }

    @Override
    public List<Comment> findAllByPostId(PostId of) {
        return null;
    }

    @Override
    public Comment updateComment(Comment comment) {
        return null;
    }
}
