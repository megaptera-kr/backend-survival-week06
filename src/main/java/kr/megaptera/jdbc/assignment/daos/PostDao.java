package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.domains.model.Post;

import java.util.List;

public interface PostDao {

    List<PostDto> findAll();

    PostDto find(String id);

    void save(Post post);

    void update(String id, Post post);

    void delete(String id);
}
