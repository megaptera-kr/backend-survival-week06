package kr.megaptera.jdbc.assignment.application.posts;

import kr.megaptera.jdbc.assignment.dtos.posts.PostReadDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final PostDao postDao;

    @Autowired
    public GetPostsService(PostDao postDao){
        this.postDao = postDao;
    }

    public List<PostReadDto> execute() {
        var entities = postDao.findAll();
        var posts = entities.stream().map(entity -> new Post(entity)).toList();
        var dtos = posts.stream().map(post -> new PostReadDto(post)).toList();
        return dtos;
    }
}
