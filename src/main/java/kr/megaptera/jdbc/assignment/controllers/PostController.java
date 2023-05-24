package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreatePostService;
import kr.megaptera.jdbc.assignment.application.DeletePostService;
import kr.megaptera.jdbc.assignment.application.GetPostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.application.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    @Autowired
    GetPostsService getPostsService;
    @Autowired
    GetPostService getPostService;
    @Autowired
    CreatePostService createPostService;
    @Autowired
    UpdatePostService updatePostService;
    @Autowired
    DeletePostService deletePostService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getList() {
        return getPostsService.getList();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(
            @PathVariable String id
    ) {
        return getPostService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(
            @RequestBody PostDto postDto
    ) {
        createPostService.createPost(postDto);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdatePost(
            @PathVariable String id
            , @RequestBody PostDto postDto
    ) {
        updatePostService.updatePost(id, postDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable String id
    ) {
        deletePostService.deletePost(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String PostNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }
}
