package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final JdbcPostDao jdbcPostDao;

    public GetPostsService(JdbcPostDao postDao) {
        this.jdbcPostDao = postDao;
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = jdbcPostDao.findAll();
        return posts.stream().map(PostDto::new).toList();
    }
}
