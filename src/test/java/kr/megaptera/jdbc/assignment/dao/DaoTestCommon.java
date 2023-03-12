package kr.megaptera.jdbc.assignment.dao;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.utils.SchemaUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.stream.IntStream;

public class DaoTestCommon {

  public static final int POST_SAMPLE_SIZE = 5;

  public static final int COMMENT_SAMPLE_SIZE = 5;

  public static void initDatabaseSchema(TransactionTemplate transactionTemplate,
                                        JdbcTemplate jdbcTemplate) {
    List<String> sqls = SchemaUtil.getCreateQueries();
    transactionTemplate.execute(status -> {
      sqls.forEach(sql -> jdbcTemplate.execute(sql));
      return null;
    });
  }

  public static void insertSamplePostData(PostDao postDao, int size) {
    IntStream.range(0, size).map((number) -> {
      postDao.save(new PostEntity("인생이란" + number, "bluesky" + number, "내용입니다." + number));
      return number;
    }).count();
  }

  public static void insertSampleCommentDataWithFirstPostId(String targetPostId,
                                                            CommentDao commentDao,
                                                            int size) {
    IntStream.range(0, size).map((number) -> {
      commentDao.save(new CommentEntity(targetPostId, "작가" + number, "내용" + number));
      return number;
    }).count();
  }

}
