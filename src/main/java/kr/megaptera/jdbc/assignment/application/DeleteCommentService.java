package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final CommentDao commentDao;

    public CommentDto deleteComment(String id, String postId) throws CommentNotFound {
        Comment comment = commentDao.find(id, postId);
        commentDao.delete(comment.getId());

        return new CommentDto(comment);
    }

}
