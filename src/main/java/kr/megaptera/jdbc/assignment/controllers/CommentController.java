package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.applications.CreateCommentService;
import kr.megaptera.jdbc.assignment.applications.DeleteCommentService;
import kr.megaptera.jdbc.assignment.applications.GetCommentsService;
import kr.megaptera.jdbc.assignment.applications.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdatedDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentDtos =
            getCommentsService.getCommentDtos(postId);

        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestParam String postId,
                             @RequestBody CommentCreateDto commentCreateDto) {
        CommentDto created = createCommentService
            .createComment(postId, commentCreateDto);

        return created;
    }

    @PatchMapping("/{id}")
    public CommentDto update(
        @PathVariable String id,
        @RequestParam String postId,
        @RequestBody CommentUpdatedDto commentUpdatedDto
    ) {
        CommentDto updated = updateCommentService
            .updateComment(id, postId, commentUpdatedDto);

        return updated;
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(
        @PathVariable String id,
        @RequestParam String postId
    ) {
        CommentDto deleted = deleteCommentService.deleteComment(id, postId);

        return deleted;
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "댓글을 찾을 수 없습니다.";
    }
}
