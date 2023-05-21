package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
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
