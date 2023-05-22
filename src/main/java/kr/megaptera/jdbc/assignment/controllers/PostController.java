package kr.megaptera.jdbc.assignment.controllers;



import kr.megaptera.jdbc.assignment.applications.*;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(GetPostsService getPostsService,
                          GetPostService getPostService,
                          CreatePostService createPostService,
                          UpdatePostService updatePostService,
                          DeletePostService deletePostService) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<PostDto> list() {
        List<PostDto> postDtos = getPostsService.getPostDtos();

        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {
        PostDto postDto = getPostService.getPostDto(id);

        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PostCreateDto postCreateDto) {
        PostDto created = createPostService.createPost(postCreateDto);

    }

    @PatchMapping("/{id}")
    public void update(@PathVariable String id,
                          @RequestBody PostUpdateDto postUpdateDto) {
        PostDto updated = updatePostService.updatePost(id, postUpdateDto);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        PostDto postDto = deletePostService.deletePost(id);

    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }
}
