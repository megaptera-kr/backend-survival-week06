package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final JdbcPostDao jdbcPostDao;

    public GetPostsService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }


    public List<PostDto> getPosts() {
        List<Post> posts = jdbcPostDao.findAll();

        return posts.stream().map(post -> new PostDto(post)).toList();
    }


}
