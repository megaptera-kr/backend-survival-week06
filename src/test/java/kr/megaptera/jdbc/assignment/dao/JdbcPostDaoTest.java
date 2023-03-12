package kr.megaptera.jdbc.assignment.dao;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static kr.megaptera.jdbc.assignment.dao.DaoTestCommon.POST_SAMPLE_SIZE;
import static kr.megaptera.jdbc.assignment.dao.DaoTestCommon.initDatabaseSchema;
import static kr.megaptera.jdbc.assignment.dao.DaoTestCommon.insertSamplePostData;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
public class JdbcPostDaoTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TransactionTemplate transactionTemplate;

  private PostDao jdbcPostDao;

  @BeforeEach
  void setUp() {
    jdbcPostDao = new JdbcPostDao(jdbcTemplate, transactionTemplate);
    initDatabaseSchema(transactionTemplate, jdbcTemplate);
    insertSamplePostData(jdbcPostDao, POST_SAMPLE_SIZE);
  }

  @Test
  void findAll() {
    //given

    //when
    List<PostEntity> postEntities = jdbcPostDao.findAll();

    //then
    Assertions.assertThat(2).isEqualTo(postEntities.size());
  }

  @Test
  void find_correct() {
    //given
    List<PostEntity> entities = jdbcPostDao.findAll();
    PostEntity target = entities.get(0);

    //when
    PostEntity postEntity = jdbcPostDao.find(target.getId());

    //then
    Assertions.assertThat(postEntity).isNotNull();
    Assertions.assertThat(postEntity.getId()).isEqualTo(target.getId());
  }

  @Test
  void find_incorrect() {
    //given

    //when
    //then
    assertThrows(PostNotFound.class, () -> {
      PostEntity postEntity = jdbcPostDao.find("9999");
    });
  }

  @Test
  void save_insert() {
    //given
    PostEntity insert = new PostEntity("세상이란", "천재", "마음먹기 나름");

    //when
    jdbcPostDao.save(insert);
    PostEntity result = jdbcPostDao.find(insert.getId());

    //then
    Assertions.assertThat(result.getId()).isEqualTo(insert.getId());
    Assertions.assertThat(result.getTitle()).isEqualTo(insert.getTitle());
    Assertions.assertThat(result.getContent()).isEqualTo(insert.getContent());
  }

  @Test
  void save_update() {
    //given
    List<PostEntity> entities = jdbcPostDao.findAll();
    PostEntity target = entities.get(0);
    target.update("바보", "바보다!");

    //when
    jdbcPostDao.save(target);
    PostEntity result = jdbcPostDao.find(target.getId());

    //then
    Assertions.assertThat(result.getId()).isEqualTo(target.getId());
    Assertions.assertThat(result.getTitle()).isEqualTo(target.getTitle());
    Assertions.assertThat(result.getContent()).isEqualTo(target.getContent());

  }

  @Test
  void delete() {
    //given
    List<PostEntity> entities = jdbcPostDao.findAll();
    int oldSize = entities.size();
    PostEntity target = entities.get(0);

    //when
    jdbcPostDao.delete(target.getId());
    int newSize = jdbcPostDao.findAll().size();

    //then
    Assertions.assertThat(newSize).isEqualTo(oldSize - 1);
  }
}
