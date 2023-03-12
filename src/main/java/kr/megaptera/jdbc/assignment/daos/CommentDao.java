package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.ArrayList;
import java.util.List;

public interface CommentDao {

    public List<Comment> findAll(PostId postId);

    public Comment find(CommentId id);

    public void save(Comment comment);

    public void delete(CommentId id) ;

}
