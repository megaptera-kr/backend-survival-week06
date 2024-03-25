package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final JdbcPostDao postDao;

    public GetPostsService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = postDao.findAll();
        return posts.stream().map(PostDto::new).toList(); //map posts를 PostDto 형식으로 변형시켜주는 역할
    }
}
