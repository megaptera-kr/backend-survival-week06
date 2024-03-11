package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.dtos.comment.CreateCommentDTO;
import kr.megaptera.jdbc.assignment.model.Comment;
import kr.megaptera.jdbc.assignment.model.PostId;
import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(String postId, CreateCommentDTO createCommentDTO) {
        Comment comment = new Comment(PostId.of(postId), createCommentDTO.author(), createCommentDTO.content());
        commentRepository.createComment(comment);
    }
}
