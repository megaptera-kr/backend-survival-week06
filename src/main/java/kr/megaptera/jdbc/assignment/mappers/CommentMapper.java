package kr.megaptera.jdbc.assignment.mappers;

import kr.megaptera.jdbc.assignment.dtos.CommentDetailDto;
import kr.megaptera.jdbc.assignment.dtos.CommentEntityDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.models.Author;
import kr.megaptera.jdbc.assignment.models.CommentContent;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;

public class CommentMapper {
    public CommentEntityDto toEntityDto(CommentEntity entity) {
        return new CommentEntityDto(
                entity.getId().toString(),
                entity.getPostId().toString(),
                entity.getAuthor().toString(),
                entity.getContent().toString()
        );
    }

    public CommentDetailDto toDetailDto(CommentEntity entity) {
        return new CommentDetailDto(
                entity.getId().toString(),
                entity.getAuthor().toString(),
                entity.getContent().toString()
        );
    }

    public CommentEntity toEntity(CommentEntityDto dto) {
        return CommentEntity.of(
                CommentId.of(dto.getId()),
                PostId.of(dto.getPostId()),
                Author.of(dto.getAuthor()),
                CommentContent.of(dto.getContent()));
    }
}
