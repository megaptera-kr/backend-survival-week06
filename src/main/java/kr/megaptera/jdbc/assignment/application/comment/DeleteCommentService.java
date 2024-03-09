package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteComment(String id, String postId) {
        commentRepository.deleteComment(id, postId);
    }
}
