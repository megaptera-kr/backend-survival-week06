package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDetailDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.mappers.PostMapper;
import kr.megaptera.jdbc.assignment.models.PostContent;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public List<PostDetailDto> list() {
        return postRepository.findAll().stream().map(postMapper::toDetailDto).toList();
    }

    public PostDetailDto detail(String id) {
        PostEntity entity = postRepository.findById(PostId.of(id));
        return postMapper.toDetailDto(entity);
    }

    public PostDetailDto create(PostCreateDto dto) {
        PostEntity entity = postMapper.toEntity(dto);
        postRepository.save(entity);
        return postMapper.toDetailDto(entity);
    }

    public PostDetailDto update(String id, PostUpdateDto dto) {
        PostEntity entity = postRepository.findById(PostId.of(id));
        entity.update(
                PostTitle.of(dto.getTitle()),
                PostContent.of(dto.getContent())
        );
        postRepository.save(entity);
        return postMapper.toDetailDto(entity);
    }

    public PostDetailDto delete(String id) {
        PostEntity entity = postRepository.findById(PostId.of(id));
        postRepository.delete(PostId.of(id));
        return postMapper.toDetailDto(entity);
    }
}
