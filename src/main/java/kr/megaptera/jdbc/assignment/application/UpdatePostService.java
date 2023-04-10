package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.UpdatePostRequest;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostDao postDao;

    public UpdatePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public void updatePost(String id, UpdatePostRequest request) {
        postDao.update(id, request.getTitle(), request.getContent());
    }
}
