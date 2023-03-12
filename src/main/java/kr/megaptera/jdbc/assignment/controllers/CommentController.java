package kr.megaptera.jdbc.assignment.controllers;

import java.util.List;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  // 특정 게시물 댓글 조회
  @GetMapping
  public List<CommentDto> getCommentsList(@RequestParam String postId) {
    return commentService.getCommentsList(postId);
  }

  // 댓글 작성
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void saveComment(
    @RequestParam String postId,
    @RequestBody CommentCreateDto commentCreateDto
  ) {
    commentService.save(postId, commentCreateDto);
  }

  // 댓글 수정
  @PatchMapping("/{commentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public CommentDto updateComment(
    @PathVariable String commentId,
    @RequestParam String postId,
    @RequestBody CommentDto updateContent
  ) {
    return commentService.updateComment(commentId, postId, updateContent);
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public CommentDto deleteComment(
    @PathVariable String commentId,
    @RequestParam String postId
  ) {
    return commentService.deleteComment(commentId);
  }
}
