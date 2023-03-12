package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final PostDao postDao;

    public PostDto addPostDto(PostDto postDto) {
        Post post = new Post(postDto.getTitle(), postDto.getAuthor(), postDto.getContent());
        postDao.save(post);
        return new PostDto(post);
    }

}
