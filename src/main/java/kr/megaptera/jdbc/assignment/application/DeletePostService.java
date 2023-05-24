package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostDao postDao;

    public DeletePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public void deletePost(String id) {
        postDao.delete(id);
    }
}
