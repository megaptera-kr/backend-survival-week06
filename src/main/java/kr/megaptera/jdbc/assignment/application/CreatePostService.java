package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.dtos.request.RqCreatePostDto;
import kr.megaptera.jdbc.assignment.models.Author;
import kr.megaptera.jdbc.assignment.models.Content;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.Title;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    private final PostDao postDao;

    public CreatePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public ResponseEntity<PostDto> createPost(RqCreatePostDto dto) {
        Post post = new Post(Title.of(dto.getTitle()),
                Author.of(dto.getAuthor()),
                Content.of(dto.getContent()));
        Post savePost = postDao.savePost(post);
        PostDto postDto = new PostDto(savePost);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
}
