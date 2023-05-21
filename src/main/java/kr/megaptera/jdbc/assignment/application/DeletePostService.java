package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {

    private final JdbcPostDao postDao;

    public DeletePostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto deletePost(String id) {
        PostDto postDto = postDao.find(id);
        if (postDto == null)
            throw new PostNotFound();
        postDao.delete(id);
        return postDto;
    }
}
