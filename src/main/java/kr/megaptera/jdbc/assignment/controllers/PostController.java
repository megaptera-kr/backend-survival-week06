package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.posts.*;
import kr.megaptera.jdbc.assignment.dtos.posts.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.posts.PostUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private GetPostsService getPostsService;
    @Autowired
    private GetPostService getPostService;
    @Autowired
    private CreatePostService createPostService;
    @Autowired
    private UpdatePostService updatePostService;
    @Autowired
    private DeletePostService deletePostService;

    @GetMapping
    public ResponseEntity findAll() {
        var posts = getPostsService.execute();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity find(@PathVariable String postId) throws PostNotFoundException {
        var post = getPostService.execute(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    private ResponseEntity add(@RequestBody PostCreateDto reqBody) {
        var post = createPostService.execute(reqBody);
        var uri = URI.create("/posts/" + post.getId());
        return ResponseEntity.created(uri).body(post);
    }

    @PatchMapping("/{postId}")
    private ResponseEntity update(@PathVariable String postId, @RequestBody PostUpdateDto reqBody) throws PostNotFoundException {
        var post = updatePostService.execute(postId, reqBody);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{postId}")
    private ResponseEntity delete(@PathVariable String postId) throws PostNotFoundException {
        var post = deletePostService.execute(postId);
        return ResponseEntity.ok(post);
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void postNotFound() {
    }
}
