package kr.megaptera.jdbc.assignment.application.posts;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.posts.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final PostDao postRepository;

    public GetPostsService(PostDao postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPosts() {
        return this.postRepository.findAll().stream().map(PostDto::of).toList();
    }
}
