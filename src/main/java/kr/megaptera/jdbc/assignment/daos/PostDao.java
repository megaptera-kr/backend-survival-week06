package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;

import java.util.List;

public interface PostDao {

    public List<Post> findAll();
    public Post find(PostId id);

    public void save(Post post);

    public void delete(PostId id);
}
