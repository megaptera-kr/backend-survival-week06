package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.applications.CreateCommentService;
import kr.megaptera.jdbc.assignment.applications.DeleteCommentService;
import kr.megaptera.jdbc.assignment.applications.GetCommentsService;
import kr.megaptera.jdbc.assignment.applications.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.CommnetNotFound;
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
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentDtos =
                getCommentsService.getCommentDtos(postId);

        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String postId,
                       @RequestBody CommentCreateDto commentCreateDto) {
        createCommentService.createComment(postId, commentCreateDto);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                       @RequestParam String postId,
                       @RequestBody CommentUpdateDto commentUpdateDto) {
        updateCommentService.update(id, postId, commentUpdateDto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id,
            @RequestParam String postId) {
        deleteCommentService.deleteComment(id, postId);
    }

    @ExceptionHandler(CommnetNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String commentNotFound() {
        return "댓글을 찾을 수 없습니다.!!";
    }
}
