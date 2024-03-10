package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.dtos.PostDto;

import java.util.List;

public interface PostDao {
    List<PostDto> getList();

    PostDto getPost(PostDto postDto);

    void insertPost(PostDto postDto);

    void updatePost(PostDto postDto);

    void deletePost(PostDto postDto);
}
