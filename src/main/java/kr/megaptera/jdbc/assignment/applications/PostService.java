package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.domains.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PostService {
    private final PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getList() {
        List<Post> posts = postDao.findAll();
        return posts.stream().map(PostDto::new).toList();
    }

    public PostDto getPost(String id) {
        Post post = postDao.find(id);
        return new PostDto(post);
    }

    public void createPost(PostCreateDto postcreateDto) {
        Post post = new Post(postcreateDto.getTitle(), postcreateDto.getAuthor(), postcreateDto.getContent()
        );
        postDao.create(post);
    }

    public void update(String id, PostUpdateDto postUpdateDto) {
        postDao.update(id, postUpdateDto);
    }

    public void delete(String id) {
        postDao.delete(id);
    }


}
