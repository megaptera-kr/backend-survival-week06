package kr.megaptera.jdbc.assignment.services;

import java.util.List;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private JdbcCommentDao jdbcCommentDao;

  public CommentService(JdbcCommentDao jdbcCommentDao) {
    this.jdbcCommentDao = jdbcCommentDao;
  }

  public List<CommentDto> getCommentsList(String postId) {
    return jdbcCommentDao
      .getCommentList(PostId.of(postId))
      .stream()
      .map(Comment::toDto)
      .toList();
  }

  public void save(String postId, CommentCreateDto commentCreateDto) {
    jdbcCommentDao.save(
      PostId.of(postId),
      new Comment(commentCreateDto.getAuthor(), commentCreateDto.getContent())
    );
  }

  public CommentDto updateComment(
    String commentId,
    String postId,
    CommentDto updateContent
  ) {
    Comment updateTarget = jdbcCommentDao.findById(CommentId.of(commentId));
    updateTarget.update(updateContent.getContent());
    jdbcCommentDao.update(updateTarget);
    return updateTarget.toDto();
  }

  public CommentDto deleteComment(String commentId) {
    Comment deleteTarget = jdbcCommentDao.findById(CommentId.of(commentId));
    jdbcCommentDao.delete(deleteTarget);
    return deleteTarget.toDto();
  }
}
