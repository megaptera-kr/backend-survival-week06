package kr.megaptera.jdbc.assignment.application.posts;

import kr.megaptera.jdbc.assignment.dtos.posts.PostReadDto;
import kr.megaptera.jdbc.assignment.dtos.posts.PostUpdateDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFoundException;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostDao postDao;

    @Autowired
    public UpdatePostService(PostDao postDao){
        this.postDao = postDao;
    }

    public PostReadDto execute(String postId, PostUpdateDto reqBody) throws PostNotFoundException {
        var oldEntity = postDao.find(postId);
        if(oldEntity == null){
            throw new PostNotFoundException();
        }

        var model = new Post(oldEntity);
        model.update(reqBody.getTitle(), reqBody.getContent());

        var newEntity = new PostEntity(model);
        postDao.update(newEntity.getId(), newEntity);

        return new PostReadDto(model);
    }
}
