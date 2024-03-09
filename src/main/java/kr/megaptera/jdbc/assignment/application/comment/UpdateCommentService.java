package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void updateComment(String commentId, String postId, String content) {
        commentRepository.updateComment(commentId, postId, content);
    }
}
