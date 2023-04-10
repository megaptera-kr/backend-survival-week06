package kr.megaptera.jdbc.assignment.applications.post;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.post.*;
import kr.megaptera.jdbc.assignment.models.post.*;
import org.springframework.stereotype.*;

@Service
public class UpdatePostService {
    private final JdbcPostDao jdbcPostDao;

    public UpdatePostService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }

    public void update(String id, PostUpdateDto postUpdateDto) {
        Post post = jdbcPostDao.find(id);

        post.update(postUpdateDto);
        jdbcPostDao.save(post);
    }
}
