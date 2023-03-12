package kr.megaptera.jdbc.assignment.application;


import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final JdbcPostDao jdbcPostDao;

    public CreatePostService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }

    public PostDto createPost(PostDto postDto) {
        Post post = new Post(
                postDto.getTitle(),
                postDto.getAuthor(),
                MultilineText.of(postDto.getContent())
        );

        jdbcPostDao.save(post);


        return new PostDto(
                post.id().toString(),
                post.title(),
                post.author(),
                post.content().toString()
        );

    }

}
