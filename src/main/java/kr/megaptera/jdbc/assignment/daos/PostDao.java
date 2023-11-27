package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostDao {
    List<Post> findAll();

    Post find(PostId id);

    void save(Post post);

    void delete(PostId id);

    void clear();

    void update(Post post);
}
