package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final JdbcPostDao jdbcPostDao;

    public CreatePostService(JdbcPostDao postDao) {
        this.jdbcPostDao = postDao;
    }

    public PostDto createPost(PostCreateDto postCreateDto) {
        Post post = new Post(
                postCreateDto.getTitle(),
                postCreateDto.getAuthor(),
                MultilineText.of(postCreateDto.getContent())
        );

        jdbcPostDao.save(post);

        return new PostDto(post);
    }
}
