package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCommentsService {

    private final CommentDao commentDao;

    public GetCommentsService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public ResponseEntity<List<CommentDto>> getCommentByPostId(int postId) {
        List<Comment> commentList = commentDao.findAllByPostId(PostId.of(postId));

        List<CommentDto> commentDtoList = commentList.stream().map(CommentDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }
}
