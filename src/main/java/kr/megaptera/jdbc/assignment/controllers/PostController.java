package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreatePostService;
import kr.megaptera.jdbc.assignment.application.DeletePostService;
import kr.megaptera.jdbc.assignment.application.GetPostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.application.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.PostCreateRequestDto;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateRequestDto;
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
  public List<PostResponseDto> list() {
    List<PostResponseDto> postDtos = getPostsService.getPosts();

    return postDtos;
  }

  @GetMapping("/{id}")
  public PostResponseDto detail(@PathVariable String id) {
    PostResponseDto postDto = getPostService.getPostDetail(id);

    return postDto;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponseDto create(@RequestBody PostCreateRequestDto postCreateDto) {
    PostResponseDto created = createPostService.createPost(postCreateDto);

    return created;
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable String id,
                     @RequestBody PostUpdateRequestDto postUpdateDto) {
    PostResponseDto updated = updatePostService.updatePost(id, postUpdateDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    PostResponseDto postDto = deletePostService.deletePost(id);
  }

  @ExceptionHandler(PostNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String postNotFound() {
    return "게시물을 찾을 수 없습니다.";
  }
}
