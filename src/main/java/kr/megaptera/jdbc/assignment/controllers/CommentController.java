package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {

    private final CreateCommentService createCommentService;
    private final DeleteCommentService deleteCommentService;
    private final GetCommentsService getCommentsService;
    private final UpdateCommentService updateCommentService;

    /**
     * 댓글 조회
     * @param postId
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentList(@RequestParam String postId) {
        return getCommentsService.getCommentList(postId);
    }

    /**
     * 댓글 생성
     * @param postId
     * @param commentDto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@RequestParam String postId, @RequestBody CommentDto commentDto) {
        return createCommentService.createComment(postId, commentDto);
    }

    /**
     * 댓글 수정
     * @param id
     * @param postId
     * @param commentDto
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable("id") String id,
                              @RequestParam String postId,
                              @RequestBody CommentDto commentDto) throws CommentNotFound {
        updateCommentService.updateComment(id, postId, commentDto);
    }

    /**
     * 댓글 삭제
     * @param id
     * @param postId
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentDto deleteComment(@PathVariable("id") String id,
                                    @RequestParam String postId) throws CommentNotFound {
        return deleteCommentService.deleteComment(id, postId);
    }

}


