package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDetailDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDetailDto> list() {
        return postService.list();
    }

    @GetMapping("/{id}")
    public PostDetailDto detail(@PathVariable String id) {
        return postService.detail(id);
    }

    @PostMapping
    public PostDetailDto create(@RequestBody PostCreateDto dto) {
        return postService.create(dto);
    }

    @PatchMapping("/{id}")
    public PostDetailDto update(@PathVariable String id, @RequestBody PostUpdateDto dto) {
        return postService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public PostDetailDto delete(@PathVariable String id) {
        return postService.delete(id);
    }
}
