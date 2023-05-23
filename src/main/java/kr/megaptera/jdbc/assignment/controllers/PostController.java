package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreatePostService;
import kr.megaptera.jdbc.assignment.application.DeletePostService;
import kr.megaptera.jdbc.assignment.application.GetPostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.application.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.dtos.request.RqCreatePostDto;
import kr.megaptera.jdbc.assignment.dtos.request.RqUpdatePostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final CreatePostService createPostService;
    private final DeletePostService deletePostService;
    private final GetPostService getPostService;
    private final GetPostsService getPostsService;
    private final UpdatePostService updatePostService;

    public PostController(CreatePostService createPostService, DeletePostService deletePostService,
                          GetPostService getPostService, GetPostsService getPostsService,
                          UpdatePostService updatePostService) {
        this.createPostService = createPostService;
        this.deletePostService = deletePostService;
        this.getPostService = getPostService;
        this.getPostsService = getPostsService;
        this.updatePostService = updatePostService;
    }

    @GetMapping()
    public ResponseEntity<List<PostDto>> getPostList () {
        return getPostsService.getPostList();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById (@PathVariable("postId") int postId) {
        return getPostService.getPostById(postId);
    }

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody RqCreatePostDto dto) {
        return createPostService.createPost(dto);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody RqUpdatePostDto dto,
                                              @PathVariable("postId") int postId) {
        return updatePostService.updatePost(dto, postId);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDto> deletePost(@PathVariable("postId") int postId) {
        return deletePostService.deletePost(postId);
    }

}
