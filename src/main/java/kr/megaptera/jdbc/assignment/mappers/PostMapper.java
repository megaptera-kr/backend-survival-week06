package kr.megaptera.jdbc.assignment.mappers;

import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDetailDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.models.Author;
import kr.megaptera.jdbc.assignment.models.PostContent;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;

public class PostMapper {
    public PostDetailDto toDetailDto(PostEntity entity) {
        return new PostDetailDto(
                entity.getId().toString(),
                entity.getAuthor().toString(),
                entity.getTitle().toString(),
                entity.getContent().toString()
        );
    }

    public PostEntity toEntity(PostDetailDto dto) {
        return PostEntity.of(
                PostId.of(dto.getId()),
                Author.of(dto.getAuthor()),
                PostTitle.of(dto.getTitle()),
                PostContent.of(dto.getContent())
        );
    }

    public PostEntity toEntity(PostCreateDto dto) {
        return PostEntity.createNew(
                Author.of(dto.getAuthor()),
                PostTitle.of(dto.getTitle()),
                PostContent.of(dto.getContent())
        );
    }
}
