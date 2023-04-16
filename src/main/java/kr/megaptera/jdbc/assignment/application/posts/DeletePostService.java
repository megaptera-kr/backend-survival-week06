package kr.megaptera.jdbc.assignment.application.posts;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.posts.PostReadDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFoundException;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostDao postDao;

    @Autowired
    public DeletePostService(PostDao postDao){
        this.postDao = postDao;
    }

    public PostReadDto execute(String postId) throws PostNotFoundException {

        var entity = postDao.find(postId);
        if(entity == null){
            throw new PostNotFoundException();
        }

        postDao.remove(postId);
        var model = new Post(entity);

        return new PostReadDto(model);
    }
}
