package kr.megaptera.jdbc.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentConcontroller {
    private final ObjectMapper objectMapper;
    @Autowired
    private CommentService commentService;

    public CommentConcontroller(CommentService commentService) {
        this.objectMapper = new ObjectMapper();
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDto> getList(@RequestParam String postId) {
        CommentDto commentDto = new CommentDto(postId);
        return commentService.getList(commentDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertComment(@RequestParam String postId, CommentDto commentDto) {
        commentService.insertComment(new CommentDto(postId
                , commentDto.getId()
                , commentDto.getContent()
                , commentDto.getAuthor()));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable("id") String id
            , @RequestParam String postId
            , @RequestBody CommentDto commentDto) {
        commentDto.setId(id);
        commentDto.setPostId(postId);
        commentService.updateComment(commentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") String id, @RequestParam String postId) {
        commentService.deleteComment(new CommentDto(id, postId));
    }
}
