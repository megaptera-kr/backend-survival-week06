package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponse;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final PostDao postDao;

    public GetPostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostResponse getPost(String id) {
        Post post = postDao.findById(id);

        return new PostResponse(post);
    }
}
