package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.applications.comment.*;
import kr.megaptera.jdbc.assignment.dtos.comment.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/comments")
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
    public List<CommentDto> list(
            @RequestParam String postId
    ) {
        List<CommentDto> commentDtos = getCommentsService.getCommentDtos(postId);
        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestParam String postId,
            @RequestBody CommentCreateDto commentCreateDto
    ) {
        createCommentService.create(postId, commentCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentUpdateDto commentUpdateDto
    ) {
        updateCommentService.updateComment(id, postId, commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id,
            @RequestParam String postId
    ) {
        deleteCommentService.delete(id, postId);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String commentNotFound() {
        return "댓글이 존재하지 않습니다.";
    }
}
