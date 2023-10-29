package kr.megaptera.jdbc.assignment.controllers.posts;

import kr.megaptera.jdbc.assignment.application.posts.CreatePostService;
import kr.megaptera.jdbc.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.jdbc.assignment.dtos.posts.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final CreatePostService createPostService;

    public PostController(CreatePostService createPostService) {
        this.createPostService = createPostService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto post(@RequestBody CreatePostDto createPostDto) {
        return createPostService.create(createPostDto);
    }
}
