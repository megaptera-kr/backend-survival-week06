package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCommentService {

    private final CommentDao commentDao;

    public CommentDto updateCommentDto(String id, String postId, CommentDto commentDto) {
        Comment comment = commentDao.find(CommentId.of(id));
        comment.update(commentDto.getContent());
        commentDao.update(comment);
        return new CommentDto(comment);
    }

}
