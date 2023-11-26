package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.services.CreatePostService;
import kr.megaptera.jdbc.assignment.services.DeletePostService;
import kr.megaptera.jdbc.assignment.services.GetPostService;
import kr.megaptera.jdbc.assignment.services.GetPostsService;
import kr.megaptera.jdbc.assignment.services.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("posts")
@RestController
public class PostController {

    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(GetPostsService getPostsService, GetPostService getPostService, CreatePostService createPostService, UpdatePostService updatePostService, DeletePostService deletePostService) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<PostDto> list() {
        return getPostsService.getPostDtos();
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable("id") String id) {
        return getPostService.getPostDto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostDto postDto) {
        PostDto createdPost = createPostService.createPostDto(postDto);
        return createdPost;
    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        return updatePostService.updatePostDto(id, postDto);
    }

    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable("id") String id) {
        return deletePostService.deletePostDto(id);
    }

}
