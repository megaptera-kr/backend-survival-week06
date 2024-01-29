package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domain.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentDao {

    void createComment(Comment comment);
    void updateComment(Comment comment);
    Comment find(String id, String postId);
    List<Comment> findAll(String postId);
    void delete(String id);


}
