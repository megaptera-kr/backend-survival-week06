package kr.megaptera.jdbc.assignment.daos;

import kr.megaptera.jdbc.assignment.domain.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostDao {

    void updatePostsave(Post post);
    void createPostSave(Post post);
    Post find(String id);
    List<Post> findAll();
    void delete(String id);


}
