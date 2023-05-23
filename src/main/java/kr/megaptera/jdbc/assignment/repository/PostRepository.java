package kr.megaptera.jdbc.assignment.repository;

import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class PostRepository {
    Map<PostId, Post> posts;

    public PostRepository() {
        this.posts = new HashMap<>();
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post find(PostId id) throws PostNotFound {
        Post post = posts.get(id);

        if (post == null) {
            throw new PostNotFound();
        }

        return post;
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }

    public void delete(PostId id) {
        posts.remove(id);
    }
}
