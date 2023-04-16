package kr.megaptera.jdbc.assignment.application.posts;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.posts.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.posts.PostReadDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreatePostService {
    private final PostDao postDao;

    @Autowired
    public CreatePostService(PostDao postDao){
        this.postDao = postDao;
    }

    public PostReadDto execute(PostCreateDto dto){
        var model = new Post(dto.getTitle(), dto.getAuthor(), dto.getContent());
        var entity = new PostEntity(model);

        postDao.add(entity);

        return new PostReadDto(model);
    }

}
