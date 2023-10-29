package kr.megaptera.jdbc.assignment.controllers.posts;

import kr.megaptera.jdbc.assignment.application.posts.CreatePostService;
import kr.megaptera.jdbc.assignment.application.posts.GetPostsService;
import kr.megaptera.jdbc.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.jdbc.assignment.dtos.posts.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final CreatePostService createPostService;
    private final GetPostsService getPostsService;

    public PostController(CreatePostService createPostService, GetPostsService getPostsService) {
        this.createPostService = createPostService;
        this.getPostsService = getPostsService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto post(@RequestBody CreatePostDto createPostDto) {
        return createPostService.create(createPostDto);
    }

    @GetMapping("")
    public List<PostDto> getPosts() {
        return getPostsService.getPosts();
    }
}
