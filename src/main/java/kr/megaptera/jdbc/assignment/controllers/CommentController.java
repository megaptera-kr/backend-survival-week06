package kr.megaptera.jdbc.assignment.controllers;


import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
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

@RestController
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
    public List<CommentDto> list(@RequestParam("postId") String postId) {

        List<CommentDto> commentDtos = getCommentsService.getCommentDto(postId);

        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestBody CommentDto reqCommentDto, @RequestParam("postId") String postId) {

        CommentDto commentDto = createCommentService.createComment(reqCommentDto, postId);

        return commentDto;
    }


    @PatchMapping("/{id}")
    public CommentDto update(
            @PathVariable("id") String id,
            @RequestParam("postId") String postId,
            @RequestBody CommentDto reqCommentDto
    ) {

        CommentDto commentDto = updateCommentService.updateComment(id, reqCommentDto);

        return commentDto;

    }


    @DeleteMapping("/{id}")
    public CommentDto delete(@PathVariable("id") String id, @RequestParam("postId") String postId) {

        CommentDto commentDto = deleteCommentService.deleteComment(id);

        return commentDto;

    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.\n";
    }


}
