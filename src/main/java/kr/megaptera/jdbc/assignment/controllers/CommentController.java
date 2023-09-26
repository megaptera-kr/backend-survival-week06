package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        return getCommentsService.getCommentDtos(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String postId,
                       @RequestBody CommentCreateDto commentCreateDto) {
        createCommentService.createComment(postId,commentCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                       @RequestParam String postId,
                       @RequestBody CommentUpdateDto commentUpdateDto) {
        updateCommentService.updateComment(id, postId, commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                       @RequestParam String postId) {
        deleteCommentService.deleteComment(id, postId);
    }


}
