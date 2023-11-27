package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostAuthor;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private PostDao postDao;

    @Autowired
    public CreatePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto createPostDto(PostDto postDto) {
        Post post = new Post(PostTitle.of(postDto.getTitle()), PostAuthor.of(postDto.getAuthor()), MultilineText.of(postDto.getContent()));
        postDao.save(post);
        return new PostDto(post);
    }
}
