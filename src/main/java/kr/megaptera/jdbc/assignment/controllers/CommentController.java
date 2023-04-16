package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.comments.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.comments.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.comments.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.comments.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.comments.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private GetCommentsService getCommentsService;
    @Autowired
    private CreateCommentService createCommentService;
    @Autowired
    private UpdateCommentService updateCommentService;
    @Autowired
    private DeleteCommentService deleteCommentService;

    @GetMapping
    private ResponseEntity find(@RequestParam String postId) {
        var comments = getCommentsService.execute(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    private ResponseEntity add(@RequestParam String postId, @RequestBody CommentCreateDto requestBody) {
        var comment = createCommentService.execute(postId, requestBody);
        return ResponseEntity.created(URI.create("/comments/" + comment.getId())).body(comment);
    }

    @PatchMapping("/{commentId}")
    private ResponseEntity update(
            @PathVariable String commentId,
            @RequestParam String postId,
            @RequestBody CommentUpdateDto reqBody) throws CommentNotFoundException {

        // TODO : (dh) will be check exist post?
        var comment = updateCommentService.execute(commentId, postId, reqBody);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    private ResponseEntity delete(@PathVariable String commentId) throws CommentNotFoundException {
        var comment = deleteCommentService.execute(commentId);
        return ResponseEntity.ok(comment);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void commentNotFound() {
    }
}
