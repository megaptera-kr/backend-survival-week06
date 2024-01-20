package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.CommentEntityDto;
import kr.megaptera.jdbc.assignment.error.JdbcCommentDaoCreateError;
import kr.megaptera.jdbc.assignment.error.JdbcCommentDaoDeleteError;
import kr.megaptera.jdbc.assignment.error.JdbcCommentDaoUpdateError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

public class JdbcCommentDao implements CommentDao {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CommentEntityDto> commentEntityDtoRowMapper = (rs, rowNum) -> {
        CommentEntityDto dto = new CommentEntityDto();
        dto.setId(rs.getString("id"));
        dto.setPostId(rs.getString("post_id"));
        dto.setAuthor(rs.getString("author"));
        dto.setContent(rs.getString("content"));
        return dto;
    };

    public JdbcCommentDao(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CommentEntityDto> selectAllByPostId(String postId) {
        final String sql = """
                SELECT id, post_id, author, content
                FROM comments
                WHERE post_id = ?
                """;
        return transactionTemplate.execute(status -> jdbcTemplate.query(sql, commentEntityDtoRowMapper, postId));
    }

    @Override
    public Optional<CommentEntityDto> selectById(String commentId, String postId) {
        final String sql = """
                SELECT id, post_id, author, content
                FROM comments
                WHERE id = ? AND post_id = ?
                """;
        return Optional.ofNullable(transactionTemplate.execute(status -> {
            try {
                return jdbcTemplate.queryForObject(sql, commentEntityDtoRowMapper, commentId, postId);
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }));
    }

    @Override
    public int insert(CommentEntityDto dto) {
        final String sql = """
                INSERT INTO comments (
                    id, author, content, post_id
                ) VALUES (
                    ?, ?, ?, ? 
                )
                """;
        Integer result = transactionTemplate.execute(status ->
                jdbcTemplate.update(sql, dto.getId(), dto.getAuthor(), dto.getContent(), dto.getPostId()));
        if (result == null) {
            throw new JdbcCommentDaoCreateError(new IllegalAccessException());
        }
        return result;
    }

    @Override
    public int delete(String id, String postId) {
        final String sql = """
                DELETE FROM comments WHERE id = ? and post_id = ?
                """;
        Integer result = transactionTemplate.execute(status -> jdbcTemplate.update(sql, id, postId));
        if (result == null) {
            throw new JdbcCommentDaoDeleteError(new IllegalAccessException());
        }
        return result;
    }

    @Override
    public int update(CommentEntityDto dto) {
        final String sql = """
                UPDATE comments SET
                    author = ?,
                    content = ?
                WHERE id = ? and post_id = ?
                """;
        Integer result = transactionTemplate.execute(status ->
                jdbcTemplate.update(sql, dto.getAuthor(), dto.getContent(), dto.getId(), dto.getPostId()));
        if (result == null) {
            throw new JdbcCommentDaoUpdateError(new IllegalAccessException());
        }
        return result;
    }
}
