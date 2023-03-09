package kr.megaptera.jdbc.assignment.daos;

import java.util.List;
import kr.megaptera.jdbc.assignment.models.Comment;

public interface CommentDao {

    public Comment save(Comment comment);

    public List<Comment> findByPostId(String postId);

    public Comment find(String id, String postId);

    public void update(String id, String postId, String content);

    public void delete(String id, String postId);

    public void clear();
}
