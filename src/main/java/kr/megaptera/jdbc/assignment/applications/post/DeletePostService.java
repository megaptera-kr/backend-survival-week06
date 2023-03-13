package kr.megaptera.jdbc.assignment.applications.post;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.models.post.*;
import org.springframework.stereotype.*;

@Service
public class DeletePostService {
    private final JdbcPostDao jdbcPostDao;

    public DeletePostService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }

    public void delete(String id) {
        Post post = jdbcPostDao.find(id);

        jdbcPostDao.delete(post.id());
    }
}
