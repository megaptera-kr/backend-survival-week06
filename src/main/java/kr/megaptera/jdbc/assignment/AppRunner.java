package kr.megaptera.jdbc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

  private final JdbcTemplate jdbcTemplate;

  public AppRunner(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void run(String... args) {
    // TODO: posts 와 comments 테이블을 생성해 주세요.

    // post 테이블생성
    String tableSql =
      """
        CREATE TABLE IF NOT EXISTS posts(
          id varchar(50) PRIMARY KEY, 
          title varchar(50), 
          author varchar(10), 
          content varchar(100)
        );

        CREATE TABLE IF NOT EXISTS comments(
          id varchar(50) PRIMARY KEY,
          postId varchar(50),
          author varchar(10),
          content varchar(100)
        );
      """;

    jdbcTemplate.execute(tableSql);
  }
}
