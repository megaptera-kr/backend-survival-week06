package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto createComment(String postId,
                                    CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(
            PostId.of(postId),
            commentCreateDto.getAuthor(),
            commentCreateDto.getContent()
        );

        commentRepository.save(comment);

        return new CommentDto(comment);
    }
}
