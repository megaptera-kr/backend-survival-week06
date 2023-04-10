package kr.megaptera.jdbc.assignment.applications.post;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.post.*;
import kr.megaptera.jdbc.assignment.models.post.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GetPostsService {
    private final JdbcPostDao jdbcPostDao;

    public GetPostsService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }

    public List<PostDto> findAll() {
        List<Post> posts = jdbcPostDao.findAll();

        return posts.stream().map(PostDto::new).toList();
    }
}
