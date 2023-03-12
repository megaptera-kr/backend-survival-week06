package kr.megaptera.jdbc.assignment.dao;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static kr.megaptera.jdbc.assignment.dao.DaoTestCommon.COMMENT_SAMPLE_SIZE;
import static kr.megaptera.jdbc.assignment.dao.DaoTestCommon.POST_SAMPLE_SIZE;
import static kr.megaptera.jdbc.assignment.dao.DaoTestCommon.initDatabaseSchema;
import static kr.megaptera.jdbc.assignment.dao.DaoTestCommon.insertSampleCommentDataWithFirstPostId;
import static kr.megaptera.jdbc.assignment.dao.DaoTestCommon.insertSamplePostData;

@JdbcTest
public class JdbcCommentDaoTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TransactionTemplate transactionTemplate;

  private CommentDao jdbcCommentDao;
  private PostDao jdbcPostDao;

  private String targetPostId = "";


  @BeforeEach
  void setUp() {
    jdbcCommentDao = new JdbcCommentDao(jdbcTemplate, transactionTemplate);
    jdbcPostDao = new JdbcPostDao(jdbcTemplate, transactionTemplate);
    initDatabaseSchema(transactionTemplate, jdbcTemplate);
    insertSamplePostData(jdbcPostDao, POST_SAMPLE_SIZE);
    targetPostId = jdbcPostDao.findAll().get(0).getId();
    insertSampleCommentDataWithFirstPostId(targetPostId, jdbcCommentDao, COMMENT_SAMPLE_SIZE);
  }


  @Test
  void findAll() {
    //given

    //when
    List<CommentEntity> commentEntities = jdbcCommentDao.findAll(targetPostId);

    //then
    Assertions.assertEquals(COMMENT_SAMPLE_SIZE, commentEntities.size());
  }

  @Test
  void find_correct() {
    //given

    //when

    //then
  }

  @Test
  void find_incorrect() {
    //given

    //when

    //then
  }

  @Test
  void save_insert() {
    //given

    //when

    //then
  }

  @Test
  void save_update() {
    //given

    //when

    //then
  }

  @Test
  void delete() {
    //given

    //when

    //then
  }
}
