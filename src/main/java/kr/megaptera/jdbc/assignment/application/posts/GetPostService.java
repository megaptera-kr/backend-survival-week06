package kr.megaptera.jdbc.assignment.application.posts;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.posts.PostReadDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFoundException;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final PostDao postDao;

    @Autowired
    public GetPostService(PostDao postDao){
        this.postDao = postDao;
    }

    public PostReadDto execute(String id) throws PostNotFoundException {
        var postEntity = postDao.find(id);
        if(postEntity == null){
            throw new PostNotFoundException();
        }

        var post = new Post(postEntity);
        var dto = new PostReadDto(post);
        return dto;
    }
}
