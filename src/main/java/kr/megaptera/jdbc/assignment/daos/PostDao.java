package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.PostDetailDto;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    List<PostDetailDto> selectAll();

    Optional<PostDetailDto> selectById(String id);

    int insert(PostDetailDto dto);

    int delete(String id);

    int update(PostDetailDto dto);
}
