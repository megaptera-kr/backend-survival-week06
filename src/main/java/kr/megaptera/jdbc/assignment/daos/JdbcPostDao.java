package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.entities.PostEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class JdbcPostDao implements PostDao {
  private final JdbcTemplate jdbcTemplate;
  private final TransactionTemplate transactionTemplate;

  public JdbcPostDao(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.transactionTemplate = transactionTemplate;
  }

  @Override
  public List<PostEntity> findAll() {
    return null;
  }

  @Override
  public PostEntity find(String id) {
    return null;
  }

  @Override
  public void save(PostEntity post) {

  }

  @Override
  public void delete(String id) {

  }
}
