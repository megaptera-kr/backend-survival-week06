package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.CommentEntityDto;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    List<CommentEntityDto> selectAllByPostId(String postId);

    int insert(CommentEntityDto dto);

    int delete(String id, String postId);

    int update(CommentEntityDto dto);

    Optional<CommentEntityDto> selectById(String commentId, String postId);
}
