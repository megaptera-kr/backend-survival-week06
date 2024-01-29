package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.PostDetailDto;
import kr.megaptera.jdbc.assignment.error.JdbcPostDaoCreateError;
import kr.megaptera.jdbc.assignment.error.JdbcPostDaoDeleteError;
import kr.megaptera.jdbc.assignment.error.JdbcPostDaoUpdateError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

public class JdbcPostDao implements PostDao {

    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PostDetailDto> postDetailDtoRowMapper = (rs, rowNum) -> {
        PostDetailDto dto = new PostDetailDto();
        dto.setId(rs.getString("id"));
        dto.setAuthor(rs.getString("author"));
        dto.setTitle(rs.getString("title"));
        dto.setContent(rs.getString("content"));
        return dto;
    };

    public JdbcPostDao(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PostDetailDto> selectAll() {
        final String sql = """
                SELECT id, author, title, content
                FROM posts
                """;
        return transactionTemplate.execute(status -> jdbcTemplate.query(sql, postDetailDtoRowMapper));
    }

    @Override
    public Optional<PostDetailDto> selectById(String id) {
        final String sql = """
                SELECT id, author, title, content
                FROM posts
                WHERE id = ?
                """;
        return Optional.ofNullable(transactionTemplate.execute(status -> {
            try {
                return jdbcTemplate.queryForObject(sql, postDetailDtoRowMapper, id);
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }));
    }

    @Override
    public int insert(PostDetailDto dto) {
        final String sql = """
                INSERT INTO posts (
                    id, author, title, content
                ) VALUES (
                    ?, ?, ?, ?
                )
                """;
        Integer result = transactionTemplate.execute(status -> jdbcTemplate.update(sql,
                dto.getId(),
                dto.getAuthor(),
                dto.getTitle(),
                dto.getContent()));
        if (result == null) {
            throw new JdbcPostDaoCreateError(new IllegalAccessException());
        }
        return result;
    }

    @Override
    public int delete(String id) {
        final String sql = """
                DELETE FROM posts
                WHERE id = ?
                """;
        Integer result = transactionTemplate.execute(status -> jdbcTemplate.update(sql, id));
        if (result == null) {
            throw new JdbcPostDaoDeleteError(new IllegalAccessException());
        }
        return result;
    }

    @Override
    public int update(PostDetailDto dto) {
        final String sql = """
                UPDATE posts SET
                    author = ?,
                    title = ?,
                    content = ?
                WHERE id = ?
                """;
        Integer result = transactionTemplate.execute(status -> jdbcTemplate.update(sql, dto.getAuthor(), dto.getTitle(), dto.getContent(), dto.getId()));
        if (result == null) {
            throw new JdbcPostDaoUpdateError(new IllegalAccessException());
        }
        return result;
    }
}
