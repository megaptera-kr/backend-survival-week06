package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.dtos.comment.GetCommentDTO;
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

    public void createComment(String postId, String author, String content) {
        Comment comment = new Comment(PostId.of(postId), author, content);
        commentRepository.createComment(comment);
    }
}
