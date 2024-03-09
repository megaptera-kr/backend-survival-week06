package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.comment.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.comment.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.comment.GetCommentService;
import kr.megaptera.jdbc.assignment.application.comment.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.comment.CreateCommentDTO;
import kr.megaptera.jdbc.assignment.dtos.comment.GetCommentDTO;
import kr.megaptera.jdbc.assignment.dtos.comment.UpdateCommentDTO;
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
public class CommentController {

    private final GetCommentService getCommentService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentService getCommentService, CreateCommentService createCommentService, UpdateCommentService updateCommentService, DeleteCommentService deleteCommentService) {
        this.getCommentService = getCommentService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<GetCommentDTO> getComments(@RequestParam(name = "postId") String postId) {
        return getCommentService.getComments(postId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestParam(name = "postId") String postId, @RequestBody CreateCommentDTO createCommentDTO) {
        createCommentService.createComment(postId, createCommentDTO.author(), createCommentDTO.content());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable(name = "id") String id, @RequestParam(name = "postId") String postId, @RequestBody UpdateCommentDTO content) {
        updateCommentService.updateComment(id, postId, content.content());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id, @RequestParam(name = "postId") String postId) {
        deleteCommentService.deleteComment(id, postId);
    }

}
