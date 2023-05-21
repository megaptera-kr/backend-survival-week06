package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {

    private final JdbcPostDao postDao;

    public GetPostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto getPost(String id) {
        PostDto postDto = postDao.find(id);
        if (postDto == null)
            throw new PostNotFound();
        return postDto;
    }
}
