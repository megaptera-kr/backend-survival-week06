package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.domains.dto.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.springframework.http.HttpStatus;
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

@RequestMapping("/comments")
@RestController
public class CommentController {

    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentsService getCommentsService, CreateCommentService createCommentService,
                             UpdateCommentService updateCommentService, DeleteCommentService deleteCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    public List<CommentDto> list(
            @RequestParam("postId") String postId
    ) {
        return getCommentsService.getComments(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(
            @RequestParam("postId") String postId,
            @RequestBody CommentCreateDto commentCreateDto
    ) {
        return createCommentService.createComment(postId, commentCreateDto);
    }

    @PatchMapping("/{id}")
    public CommentDto update(
            @PathVariable String id,
            @RequestParam("postId") String postId,
            @RequestBody CommentUpdateDto commentUpdateDto
    ) {
        return updateCommentService.updateComment(id, postId, commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(
            @PathVariable String id,
            @RequestParam String postId
    ) {
        return deleteCommentService.deleteComment(id, postId);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String commentNotFound() {
        return "There's no comment you want to see.\n";
    }
}
