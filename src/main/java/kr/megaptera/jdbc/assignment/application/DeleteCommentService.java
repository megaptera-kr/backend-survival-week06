package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final CommentDao commentDao;

    public CommentDto removeCommentDto(String id) {
        Comment comment = commentDao.find(CommentId.of(id));
        commentDao.delete(CommentId.of(id));
        return new CommentDto(comment);
    }

}
