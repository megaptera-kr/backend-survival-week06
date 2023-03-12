package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class JdbcCommentDao implements CommentDao {
  private final JdbcTemplate jdbcTemplate;
  private final TransactionTemplate transactionTemplate;

  public JdbcCommentDao(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.transactionTemplate = transactionTemplate;
  }

  @Override
  public List<CommentEntity> findAll(String postId) {
    return null;
  }

  @Override
  public CommentEntity find(String id, String postId) {
    return null;
  }

  @Override
  public void save(CommentEntity comment) {

  }

  @Override
  public void delete(String commentId) {

  }
}
