package kr.megaptera.jdbc.assignment.applications.post;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.post.*;
import kr.megaptera.jdbc.assignment.models.post.*;
import org.springframework.stereotype.*;

@Service
public class CreatePostService {

    private final JdbcPostDao jdbcPostDao;

    public CreatePostService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }

    public void create(PostCreateDto postCreateDto) {
        jdbcPostDao.save(new Post(
                postCreateDto.getTitle(),
                postCreateDto.getAuthor(),
                MultilineText.of(postCreateDto.getContent())
        ));
    }
}
