package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.posts.Post;

import java.util.List;

public interface PostDao {
    public void save(Post post);

    public List<Post> findAll();
}
