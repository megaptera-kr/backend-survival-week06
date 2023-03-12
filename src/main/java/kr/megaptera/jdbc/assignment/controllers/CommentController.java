package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateRequestDto;
import kr.megaptera.jdbc.assignment.dtos.CommentResponseDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateRequestDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
  private final GetCommentsService getCommentsService;
  private final CreateCommentService createCommentService;
  private final UpdateCommentService updateCommentService;
  private final DeleteCommentService deleteCommentService;

  public CommentController(GetCommentsService getCommentsService,
                           CreateCommentService createCommentService,
                           UpdateCommentService updateCommentService,
                           DeleteCommentService deleteCommentService) {
    this.getCommentsService = getCommentsService;
    this.createCommentService = createCommentService;
    this.updateCommentService = updateCommentService;
    this.deleteCommentService = deleteCommentService;
  }

  @GetMapping
  public List<CommentResponseDto> list(@RequestParam String postId) {
    List<CommentResponseDto> commentDtos =
      getCommentsService.getComments(postId);

    return commentDtos;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommentResponseDto create(@RequestParam String postId,
                                   @RequestBody CommentCreateRequestDto commentCreateDto) {
    CommentResponseDto created = createCommentService
      .createComment(postId, commentCreateDto);

    return created;
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(
    @PathVariable String id,
    @RequestParam String postId,
    @RequestBody CommentUpdateRequestDto commentUpdateDto
  ) {
    CommentResponseDto updated = updateCommentService
      .updateComment(id, postId, commentUpdateDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(
    @PathVariable String id,
    @RequestParam String postId
  ) {
    CommentResponseDto deleted = deleteCommentService.deleteComment(id, postId);
  }

  @ExceptionHandler(CommentNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String postNotFound() {
    return "댓글을 찾을 수 없습니다.";
  }
}
