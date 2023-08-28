package kr.megaptera.jdbc.assignment.applications;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final JdbcPostDao jdbcPostDao;

    public CreatePostService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }


    public void createPost(PostCreateDto postCreateDto) {
        Post post = new Post(
          postCreateDto.getTitle(),
          postCreateDto.getAuthor(),
          MultilineText.of(postCreateDto.getContent())
        );

        jdbcPostDao.save(post);
    }
}
