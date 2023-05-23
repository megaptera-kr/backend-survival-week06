package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.dtos.request.RqCreateCommentDto;
import kr.megaptera.jdbc.assignment.dtos.request.RqUpdateCommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CreateCommentService createCommentService;
    private final DeleteCommentService deleteCommentService;
    private final GetCommentsService getCommentsService;
    private final UpdateCommentService updateCommentService;

    public CommentController(CreateCommentService createCommentService,
                             DeleteCommentService deleteCommentService,
                             GetCommentsService getCommentsService,
                             UpdateCommentService updateCommentService) {
        this.createCommentService = createCommentService;
        this.deleteCommentService = deleteCommentService;
        this.getCommentsService = getCommentsService;
        this.updateCommentService = updateCommentService;
    }

    @GetMapping()
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@RequestParam("postId") int postId) {
        return getCommentsService.getCommentByPostId(postId);
    }

    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@RequestBody RqCreateCommentDto dto,
                                                    @RequestParam("postId") int postId) {
        return createCommentService.createComment(dto, postId);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody RqUpdateCommentDto dto,
                                                    @PathVariable("commentId") int commentId,
                                                    @RequestParam("postId") int postId) {
        return updateCommentService.updateComment(dto, postId, commentId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable("commentId") int commentId,
                                                    @RequestParam("postId") int postId) {
        return deleteCommentService.deleteComment(postId, commentId);
    }
}
