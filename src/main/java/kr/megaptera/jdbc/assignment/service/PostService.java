package kr.megaptera.jdbc.assignment.service;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final JdbcPostDao postDao;

    public PostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getList() {
        return postDao.getList();
    }

    public PostDto getPost(PostDto postDto) {
        return postDao.getPost(postDto);
    }

    public void insertPost(PostDto postDto) {
        Post post = new Post(postDto);
        PostDto newPostDto = new PostDto(post);
        postDao.insertPost(newPostDto);
    }

    public void updatePost(PostDto postDto) {
        postDao.updatePost(postDto);
    }

    public void deletePost(PostDto postDto) {
        postDao.deletePost(postDto);
    }
}
