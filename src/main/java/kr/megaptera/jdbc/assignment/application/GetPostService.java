package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final PostDao postDao;

    public GetPostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto getPostDto(String id) {
        Post post = postDao.find(PostId.of(id));
        return new PostDto(post);
    }
}
