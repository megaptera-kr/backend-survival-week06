package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.dtos.comment.UpdateCommentDTO;
import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void updateComment(String commentId, String postId, UpdateCommentDTO updateCommentDTO) {
        commentRepository.updateComment(commentId, postId, updateCommentDTO.content());
    }
}
