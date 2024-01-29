package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDetailDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.mappers.CommentMapper;
import kr.megaptera.jdbc.assignment.models.Author;
import kr.megaptera.jdbc.assignment.models.CommentContent;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDetailDto> list(String postId) {
        return commentRepository.findAllByPostId(PostId.of(postId))
                .stream()
                .map(commentMapper::toDetailDto)
                .toList();
    }

    public void create(String postId, CommentCreateDto dto) {
        CommentEntity entity = CommentEntity.generate(
                PostId.of(postId),
                Author.of(dto.getAuthor()),
                CommentContent.of(dto.getContent()));
        commentRepository.save(entity);
    }


    public void update(String id, CommentUpdateDto dto, String postId) {
        CommentEntity entity = commentRepository.findById(
                CommentId.of(id),
                PostId.of(postId));
        entity.update(CommentContent.of(dto.getContent()));
        commentRepository.save(entity);
    }

    public void delete(String id, String postId) {
        commentRepository.delete(
                CommentId.of(id),
                PostId.of(postId));
    }
}
