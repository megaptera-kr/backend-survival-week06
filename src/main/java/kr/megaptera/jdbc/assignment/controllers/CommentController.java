package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getList(
            @RequestParam String postId
    ) {
        List<CommentDto> dtoList = getCommentsService.getComments(postId);
        System.out.println("controller dtolist" + dtoList.toString());
        return dtoList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) {
        createCommentService.createComment(commentDto, postId);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @RequestBody CommentDto commentDto,
            @RequestParam String postId,
            @PathVariable String id
    ) {
        System.out.println("controller" + postId + "|" + id);
        updateCommentService.update(id, commentDto, postId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id,
            @RequestParam String postId
    ) {
        System.out.println("controller" + postId + "|" + id.toString());
        deleteCommentService.delete(id, postId);
    }
}
