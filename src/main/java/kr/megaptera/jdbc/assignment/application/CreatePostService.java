package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostContent;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostDao postDao;

    public CreatePostService(PostDao postDao) {
        this.postDao = postDao;
    }


    public void createPost(PostDto postDto) {
        Post post = new Post(PostTitle.of(postDto.getTitle()), postDto.getAuthor(), PostContent.of(postDto.getContent()));
        postDao.save(post);
    }
}
