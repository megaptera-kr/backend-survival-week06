package kr.megaptera.jdbc.assignment.applications.post;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.post.*;
import kr.megaptera.jdbc.assignment.models.post.*;
import org.springframework.stereotype.*;

@Service
public class GetPostService {

    private final JdbcPostDao jdbcPostDao;

    public GetPostService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }

    public PostDto find(String id) {
        Post post = jdbcPostDao.find(id);
        return new PostDto(post);
    }
}
