package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final GetPostsService getPostsService;

    private final GetPostService getPostService;

    private final CreatePostService createPostService;

    private final UpdatePostService updatePostService;

    private final DeletePostService deletePostService;

    @GetMapping
    public List<PostDto> getPostDtos() {
        List<PostDto> postDtos = getPostsService.getPostDtos();
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPostDto(@PathVariable String id) {
        PostDto postDto = getPostService.getPostDto(id);
        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addPostDto(@RequestBody PostDto postDto) {
        PostDto createdPostDto = createPostService.addPostDto(postDto);
        return createdPostDto;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PostDto updatePostDto(@PathVariable String id, @RequestBody PostDto postDto) {
        PostDto updatedPostDto = updatePostService.updatePostDto(id, postDto);
        return updatedPostDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PostDto removePostDto(@PathVariable String id) {
        PostDto deletedPostDto = deletePostService.removePostDto(id);
        return deletedPostDto;
    }

}
