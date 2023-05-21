package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.dtos.request.RqCreateCommentDto;
import kr.megaptera.jdbc.assignment.models.Author;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.Content;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final CommentDao commentDao;

    public CreateCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public ResponseEntity<CommentDto> createComment(RqCreateCommentDto dto, int postId) {
        Comment comment = new Comment(PostId.of(postId),
                Author.of(dto.getAuthor()),
                Content.of(dto.getContent()));

        Comment saveComment = commentDao.saveComment(comment);
        CommentDto commentDto = new CommentDto(saveComment);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }
}
