package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostsService {

    private final JdbcPostDao postDao;

    public List<PostDto> getPostsDto() {
        List<Post> posts = postDao.findAll();

        List<PostDto> postDtos = posts.stream()
                .map(post -> new PostDto(post))
                .toList();

        return postDtos;
    }
}
