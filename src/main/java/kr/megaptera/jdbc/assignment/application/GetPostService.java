package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final JdbcPostDao jdbcPostDao;

    public GetPostService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }

    public PostDto getPostDto(String id) {
        Post post = jdbcPostDao.find(PostId.of(id));

        return new PostDto(post);
    }


}
