package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponse;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostDao postDao;

    public CreatePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostResponse createPost(Post post) {
        Post saved = postDao.save(post);

        return new PostResponse(saved);
    }
}
