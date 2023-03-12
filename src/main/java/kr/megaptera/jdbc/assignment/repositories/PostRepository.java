package kr.megaptera.jdbc.assignment.repositories;

import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    private final Map<PostId, Post> posts;

    public PostRepository() {
        this.posts = new HashMap<PostId, Post>();
    }

    public List<Post> findAll() {

        return new ArrayList<>(posts.values());
    }

    public Post find(PostId id) {
        Post post = posts.get(id);
        if (post == null) {
            throw new PostNotFound();
        }

        return posts.get(id);
    }

    public void save(Post post) {

        posts.put(post.id(), post);
    }

    public void delete(PostId id) {

        posts.remove(id);

    }

}
