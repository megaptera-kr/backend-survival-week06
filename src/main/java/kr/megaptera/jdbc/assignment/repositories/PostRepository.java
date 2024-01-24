package kr.megaptera.jdbc.assignment.repositories;

import kr.megaptera.jdbc.assignment.domain.Post;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    Map<String, Post> postMap;

    public PostRepository() {
        this.postMap = new HashMap<>();
    }

    public Post find(String id) throws PostNotFound {
        Post post = postMap.get(id);

        if (post == null) {
            throw new PostNotFound();
        }
        return post;
    }

    public List<Post> findAll() {
        return new ArrayList<>(postMap.values());
    }

    public Post save(Post post) {
        return postMap.put(post.getId(), post);
    }

    public boolean delete(String id) {
        postMap.remove(id);
        return true;
    }
}
