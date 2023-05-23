package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostCreateDto;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.domains.model.Post;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    private final JdbcPostDao postDao;

    public CreatePostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto createPost(PostCreateDto postCreateDto) {
        // 각 과정을 나누어서 써주어야 테스트 시 이상 없음
        Post post = new Post(postCreateDto);
        postDao.save(new Post(postCreateDto));
        return new PostDto(post);
    }
}
