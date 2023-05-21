package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostService {
    private final PostDao postDao;

    public GetPostService(PostDao postDao) {
        this.postDao = postDao;
    }


    public PostDto getPost(String id) {
        Post post = postDao.findOne(id);
        return new PostDto(post);
    }
}
