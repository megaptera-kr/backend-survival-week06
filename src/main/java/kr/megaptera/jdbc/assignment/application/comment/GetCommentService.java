package kr.megaptera.jdbc.assignment.application.comment;

import kr.megaptera.jdbc.assignment.dtos.comment.GetCommentDTO;
import kr.megaptera.jdbc.assignment.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentService {

    private final CommentRepository commentRepository;

    public GetCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<GetCommentDTO> getComments(String postId) {
        return commentRepository.getComments(postId).stream()
                .map(GetCommentDTO::of)
                .toList();
    }
}
