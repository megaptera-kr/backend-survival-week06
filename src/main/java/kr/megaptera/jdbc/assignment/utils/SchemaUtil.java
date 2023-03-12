package kr.megaptera.jdbc.assignment.utils;

import java.util.ArrayList;
import java.util.List;

public class SchemaUtil {
  private static List<String> sqls = new ArrayList<>();

  public static List<String> getCreateQueries() {
    return List.of(
      """
        DROP TABLE IF EXISTS comment
        """,
      """
        DROP TABLE IF EXISTS post
        """,
      """
        create table if not exists post(
            id CHAR(10) primary key,
            title VARCHAR (50) not null,
            author VARCHAR (50) not null,
            content VARCHAR (255) not null
            );
        """,
      """
        create table if not exists comment(
            id CHAR(10) primary key,
            post_id VARCHAR (50) not null,
            author VARCHAR (50) not null,
            content VARCHAR (255) not null,
            constraint fk_post
            foreign key(post_id) references post(id)
            );
        """
    );
  }
}
