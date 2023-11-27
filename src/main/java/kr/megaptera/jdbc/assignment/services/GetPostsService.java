package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final PostDao postDao;

    @Autowired
    public GetPostsService(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = this.postDao.findAll();
        return posts.stream().map(PostDto::new).toList();
    }
}
