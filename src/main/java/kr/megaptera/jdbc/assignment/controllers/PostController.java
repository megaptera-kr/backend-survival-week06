package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.applications.post.*;
import kr.megaptera.jdbc.assignment.dtos.post.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    private final GetPostService getPostService;
    private final GetPostsService getPostsService;
    private final UpdatePostService updatePostService;
    private final CreatePostService createPostService;
    private final DeletePostService deletePostService;

    public PostController(GetPostService getPostService,
                          GetPostsService getPostsService,
                          UpdatePostService updatePostService,
                          CreatePostService createPostService,
                          DeletePostService deletePostService) {
        this.getPostService = getPostService;
        this.getPostsService = getPostsService;
        this.updatePostService = updatePostService;
        this.createPostService = createPostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<PostDto> findAll() {
        List<PostDto> dtos = getPostsService.findAll();
        return dtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(
            @PathVariable String id
    ) {
        PostDto postDto = getPostService.find(id);
        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody PostCreateDto postCreateDto
    ) {
        createPostService.create(postCreateDto);
    }

    @PatchMapping("/{id}")
    public void update(
            @PathVariable String id,
            @RequestBody PostUpdateDto postUpdateDto
    ) {
        updatePostService.update(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable String id
    ) {
        deletePostService.delete(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "Post Not Found..";
    }
}
