package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {

    private PostDao postDao;

    @Autowired
    public GetPostService(PostDao postDao) {
        this.postDao = postDao;
    }


    public PostDto getPostDto(String id) {
        Post post = postDao.find(PostId.of(id));
        if (post == null) {
            throw new PostNotFound();
        }
        return new PostDto(post);
    }
}
