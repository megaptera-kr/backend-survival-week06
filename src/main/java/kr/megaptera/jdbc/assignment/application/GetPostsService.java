package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostsService {

    private final PostDao postDao;

    public List<PostDto> getPostDtos() {
        List<Post> posts = postDao.findAll();
        return posts.stream().map(post -> new PostDto(post)).toList();
    }

}
