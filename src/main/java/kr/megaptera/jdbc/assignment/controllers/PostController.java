package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.post.CreatePostService;
import kr.megaptera.jdbc.assignment.application.post.DeletePostServcie;
import kr.megaptera.jdbc.assignment.application.post.GetPostService;
import kr.megaptera.jdbc.assignment.application.post.GetPostsService;
import kr.megaptera.jdbc.assignment.application.post.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.post.CreatePostDTO;
import kr.megaptera.jdbc.assignment.dtos.post.GetPostDTO;
import kr.megaptera.jdbc.assignment.dtos.post.UpdatePostDTO;
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

@RestController
@RequestMapping("/posts")
public class PostController {

    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostServcie deletePostServcie;

    public PostController(GetPostsService getPostsService, GetPostService getPostService
            , CreatePostService createPostService, UpdatePostService updatePostService , DeletePostServcie deletePostServcie) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostServcie = deletePostServcie;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetPostDTO> getPosts() {
        return getPostsService.getPosts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetPostDTO getPost(@PathVariable("id") String id) {
        return getPostService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GetPostDTO createPost(@RequestBody CreatePostDTO createPostDTO) {
        return createPostService.createPost(createPostDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable("id") String id, @RequestBody UpdatePostDTO updatePostDTO) {
        updatePostService.updatePost(id, updatePostDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") String id) {
        deletePostServcie.deletePost(id);
    }
}
