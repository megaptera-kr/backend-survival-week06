package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.stereotype.*;

@Service
public class CreatePostService {
    private final JdbcPostDao postDao;

    public CreatePostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public void createPost(PostDto postCreateDto) {
        System.out.println("-------->" + postCreateDto.getId());
        Post post = new Post(
                postCreateDto.getTitle(),
                postCreateDto.getAuthor(),
                MultilineText.of(postCreateDto.getContent())
        );

        postDao.save(post);
    }
}
