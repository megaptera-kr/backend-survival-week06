package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostDao postDao;

    public DeletePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto deletePost(String id) {
        Post post = postDao.find(PostId.of(id));

        postDao.delete(PostId.of(id));

        return new PostDto(post);
    }
}
