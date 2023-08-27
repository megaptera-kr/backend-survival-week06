package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.applications.CreatePostService;
import kr.megaptera.jdbc.assignment.applications.DeletePostService;
import kr.megaptera.jdbc.assignment.applications.GetPostService;
import kr.megaptera.jdbc.assignment.applications.GetPostsService;
import kr.megaptera.jdbc.assignment.applications.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
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
@CrossOrigin
public class PostController {
    private final GetPostService getPostService;
    private final GetPostsService getPostsService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(GetPostService getPostService,
                          GetPostsService getPostsService,
                          CreatePostService createPostService,
                          UpdatePostService updatePostService,
                          DeletePostService deletePostService
    ) {
        this.getPostService = getPostService;
        this.getPostsService = getPostsService;
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
        createPostService.createPost(postCreateDto);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                       @RequestBody PostUpdateDto postUpdateDto) {
        updatePostService.updatePost(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deletePostService.deletePost(id);
    }


    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }


}
