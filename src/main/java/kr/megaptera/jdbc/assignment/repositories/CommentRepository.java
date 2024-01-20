package kr.megaptera.jdbc.assignment.repositories;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentEntityDto;
import kr.megaptera.jdbc.assignment.entities.CommentEntity;
import kr.megaptera.jdbc.assignment.mappers.CommentMapper;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    private final CommentDao commentDao;
    private final CommentMapper commentMapper;

    public CommentRepository(CommentDao commentDao, CommentMapper commentMapper) {
        this.commentDao = commentDao;
        this.commentMapper = commentMapper;
    }

    public List<CommentEntity> findAllByPostId(PostId postId) {
        return commentDao.selectAllByPostId(postId.toString()).stream().map(commentMapper::toEntity).toList();
    }

    public CommentEntity findById(CommentId commentId, PostId postId) {
        return commentDao.selectById(commentId.toString(), postId.toString()).map(commentMapper::toEntity).orElse(null);
    }

    public CommentEntity save(CommentEntity entity) {
        CommentEntity old = findById(entity.getId(), entity.getPostId());
        CommentEntityDto dtoToSave = commentMapper.toEntityDto(entity);
        if (old == null) {
            commentDao.insert(dtoToSave);
        } else {
            commentDao.update(dtoToSave);
        }
        return entity;
    }

    public CommentEntity delete(CommentId commentId, PostId postId) {
        CommentEntity entity = findById(commentId, postId);
        if (entity != null) {
            commentDao.delete(commentId.toString(), postId.toString());
        }
        return entity;
    }
}
