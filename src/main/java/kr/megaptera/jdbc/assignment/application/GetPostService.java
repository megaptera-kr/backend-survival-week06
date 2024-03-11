package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final JdbcPostDao postDao;

    public GetPostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }
    public PostDto getPostDto(String id) {
        Post post = postDao.find(PostId.of(id)); //새롭게 생성된 id값을 조회한다. of메서드 id값 새롭게 생성
        return new PostDto(post);
    }
}
