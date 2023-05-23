package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreatePostService;
import kr.megaptera.jdbc.assignment.application.DeletePostService;
import kr.megaptera.jdbc.assignment.application.GetPostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.application.UpdatePostService;
import kr.megaptera.jdbc.assignment.domains.dto.PostCreateDto;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.domains.dto.PostUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
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

@RequestMapping("/posts")
@RestController
public class PostController {

    private final GetPostService getPostService;
    private final GetPostsService getPostsService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(GetPostService getPostService, GetPostsService getPostsService,
                          CreatePostService createPostService, UpdatePostService updatePostService,
                          DeletePostService deletePostService) {
        this.getPostService = getPostService;
        this.getPostsService = getPostsService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<PostDto> list() {
        return getPostsService.getPosts();
    }

    @GetMapping("/{id}")
    public PostDto detail(
            @PathVariable String id
    ) {
        return getPostService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(
            @RequestBody PostCreateDto postCreateDto
    ) {
        return createPostService.createPost(postCreateDto);
    }

    @PatchMapping("/{id}")
    public PostDto update(
            @RequestBody PostUpdateDto postUpdateDto,
            @PathVariable String id
    ) {
        return updatePostService.updatePost(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    public PostDto delete(
            @PathVariable String id
    ) {
        return deletePostService.deletePost(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "There's no post you want to see.\n";
    }
}
