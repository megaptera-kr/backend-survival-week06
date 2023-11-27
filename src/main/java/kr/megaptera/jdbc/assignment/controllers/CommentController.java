package kr.megaptera.jdbc.assignment.controllers;


import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.services.CreateCommentService;
import kr.megaptera.jdbc.assignment.services.DeleteCommentService;
import kr.megaptera.jdbc.assignment.services.GetCommentsService;
import kr.megaptera.jdbc.assignment.services.UpdateCommentService;
import org.springframework.http.HttpStatus;
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

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {


    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentsService getCommentsService, CreateCommentService createCommentService, UpdateCommentService updateCommentService, DeleteCommentService deleteCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    public List<CommentDto> list(@RequestParam("postId") String postId) {
        return getCommentsService.getCommentDtos(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestParam("postId") String postId, @RequestBody CommentDto commentDto) {
        return createCommentService.createCommentDto(postId, commentDto);
    }

    @PatchMapping("/{id}")
    public CommentDto update(@PathVariable("id") String commentId, @RequestBody CommentDto commentDto) {
        return updateCommentService.updateCommentDto(commentId, commentDto);
    }

    @DeleteMapping("{id}")
    public CommentDto delete(@PathVariable("id") String commentId) {
        return deleteCommentService.delete(commentId);
    }

}
