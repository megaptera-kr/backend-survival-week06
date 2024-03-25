package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final JdbcPostDao postDao;

    public DeletePostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public void delete(String id) {
        postDao.delete(PostId.of(id));
    }
}
