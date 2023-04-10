package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final GetCommentsService getCommentsService;

    private final CreateCommentService createCommentService;

    private final UpdateCommentService updateCommentService;

    private final DeleteCommentService deleteCommentService;

    @GetMapping
    public List<CommentDto> getCommentDtos(@RequestParam String postId) {
        List<CommentDto> commentDtos = getCommentsService.getCommentDtos(postId);
        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addCommentDto(@RequestParam String postId, @RequestBody CommentDto commentDto) {
        CommentDto createdCommentDto = createCommentService.addCommentDto(postId, commentDto);
        return createdCommentDto;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentDto updateCommentDto(@PathVariable String id, @RequestParam String postId,
                                       @RequestBody CommentDto commentDto) {
        CommentDto updatedCommentDto = updateCommentService.updateCommentDto(id, postId, commentDto);
        return updatedCommentDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentDto removeCommentDto(@PathVariable String id) {
        CommentDto deletedCommentDto = deleteCommentService.removeCommentDto(id);
        return deletedCommentDto;
    }

}
