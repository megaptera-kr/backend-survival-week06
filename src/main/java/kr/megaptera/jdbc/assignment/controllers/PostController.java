package kr.megaptera.jdbc.assignment.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/posts")
public class PostController {
    private final ObjectMapper objectMapper;

    @Autowired
    private PostService postService;

    public PostController() {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping()
    public List<PostDto> getList() {
        return postService.getList();
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable("id") String id) {
        PostDto postDto = new PostDto(id);
        return postService.getPost(postDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertPost(@RequestBody PostDto postDto) {
        postService.insertPost(postDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        postDto.setId(id);
        postService.updatePost(postDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") String id) {
        postService.deletePost(new PostDto(id));
    }
}
