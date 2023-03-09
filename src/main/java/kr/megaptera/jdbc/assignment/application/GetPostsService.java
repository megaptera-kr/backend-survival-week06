package kr.megaptera.jdbc.assignment.application;

import java.util.List;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponse;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

@Service
public class GetPostsService {

    private final PostDao postDao;

    public GetPostsService(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostResponse> getPostList() {
        List<Post> posts = postDao.findAll();

        return posts.stream().map(PostResponse::new).toList();
    }
}
