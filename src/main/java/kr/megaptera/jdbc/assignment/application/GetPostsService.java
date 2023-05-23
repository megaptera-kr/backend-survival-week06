package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {

    private final JdbcPostDao postDao;

    public GetPostsService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getPosts() {
        return postDao.findAll();
    }
}
