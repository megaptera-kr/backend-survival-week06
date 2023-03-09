package kr.megaptera.jdbc.assignment.daos;

import java.util.List;
import kr.megaptera.jdbc.assignment.models.Post;

public interface PostDao {

    public void save(Post post);

    public List<Post> findAll();

    public Post findById(String id);

    public void update(String id, String title, String content);

    public void delete(String id);

    public void clean();
}
