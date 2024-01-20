package kr.megaptera.jdbc.assignment.repositories;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDetailDto;
import kr.megaptera.jdbc.assignment.entities.PostEntity;
import kr.megaptera.jdbc.assignment.mappers.PostMapper;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {
    private final PostDao postDao;
    private final PostMapper postMapper;

    public PostRepository(PostDao postDao, PostMapper postMapper) {
        this.postDao = postDao;
        this.postMapper = postMapper;
    }

    public List<PostEntity> findAll() {
        return postDao.selectAll().stream().map(postMapper::toEntity).toList();
    }

    public PostEntity findById(PostId postId) {
        return postDao.selectById(postId.toString())
                .map(postMapper::toEntity)
                .orElse(null);
    }

    public PostEntity save(PostEntity entity) {
        PostEntity old = findById(entity.getId());
        PostDetailDto dtoToSave = postMapper.toDetailDto(entity);
        if (old == null) {
            postDao.insert(dtoToSave);
        } else {
            postDao.update(dtoToSave);
        }
        return entity;
    }

    public PostEntity delete(PostId postId) {
        PostEntity entity = findById(postId);
        if (entity != null) {
            postDao.delete(postId.toString());
        }
        return entity;
    }

}
