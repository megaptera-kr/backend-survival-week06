package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.domains.model.Comment;

import java.util.List;

public interface CommentDao {

    List<CommentDto> findByPost(String postId);

    CommentDto find(String id, String postId);

    void save(String postId, Comment comment);

    void update(String id, String postId, Comment comment);

    void delete(String id, String postId);

}
