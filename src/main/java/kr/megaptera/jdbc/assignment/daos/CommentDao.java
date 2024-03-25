package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;

import java.util.List;

public interface CommentDao {
    List<CommentDto> getList(CommentDto commentDto);

    void insertComment(CommentDto commentDto);

    void updateComment(CommentDto commentDto);

    void deleteComment(CommentDto commentDto);
}
