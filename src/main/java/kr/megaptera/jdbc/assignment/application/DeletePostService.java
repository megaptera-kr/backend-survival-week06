package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final JdbcPostDao jdbcPostDao;

    public DeletePostService(JdbcPostDao postDao) {
        this.jdbcPostDao = postDao;
    }

    public PostDto deletePost(String id) {
        Post post = jdbcPostDao.find(PostId.of(id));

        jdbcPostDao.delete(PostId.of(id));

        return new PostDto(post);
    }
}
