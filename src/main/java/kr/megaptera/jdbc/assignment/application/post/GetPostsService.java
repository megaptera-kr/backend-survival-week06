package kr.megaptera.jdbc.assignment.application.post;

import kr.megaptera.jdbc.assignment.dtos.post.GetPostDTO;
import kr.megaptera.jdbc.assignment.model.Post;
import kr.megaptera.jdbc.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {

    private final PostRepository postRepository;

    public GetPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<GetPostDTO> getPosts() {
        List<Post> posts = postRepository.getPosts();
        return posts.stream()
                .map(GetPostDTO::of)
                .toList();
    }
}
