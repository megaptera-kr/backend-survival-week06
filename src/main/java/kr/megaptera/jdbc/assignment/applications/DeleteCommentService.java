package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto deleteComment(String id, String postId) {
        Comment comment = commentRepository
            .find(CommentId.of(id), PostId.of(postId));

        commentRepository.delete(comment.id());

        return new CommentDto(comment);
    }
}
