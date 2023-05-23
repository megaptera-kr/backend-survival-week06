package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    GetCommentsService getCommentsService;
    @Autowired
    CreateCommentService createCommentService;
    @Autowired
    UpdateCommentService updateCommentService;
    @Autowired
    DeleteCommentService deleteCommentService;

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentDtos =
                getCommentsService.getCommentDtos(postId);

        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String postId,
                       @RequestBody CommentDto commentCreateDto) {
        createCommentService.create(postId, commentCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentDto commentUpdatedDto
    ) {
        updateCommentService.updateComment(id, postId, commentUpdatedDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id,
            @RequestParam String postId
    ) {
        deleteCommentService.deleteComment(id, postId);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "댓글을 찾을 수 없습니다.";
    }
}
