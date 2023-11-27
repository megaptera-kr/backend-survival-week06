package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentDao {

    List<Comment> findAll(String postId);

    Comment find(CommentId commentId);

    void save(Comment comment);

    void delete(CommentId commentId);

    void clear();

    void update(Comment comment);
}
