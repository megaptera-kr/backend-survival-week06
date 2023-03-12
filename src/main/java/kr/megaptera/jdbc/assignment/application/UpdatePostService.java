package kr.megaptera.jdbc.assignment.application;


import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final JdbcPostDao jdbcPostDao;

    public UpdatePostService(JdbcPostDao jdbcPostDao) {
        this.jdbcPostDao = jdbcPostDao;
    }

    public PostDto updatePost(String id, PostDto postDto) {
        Post post = jdbcPostDao.find(PostId.of(id));
        post.update(
                postDto.getTitle(),
                postDto.getAuthor(),
                MultilineText.of(postDto.getContent())
        );

        Post newPost = jdbcPostDao.find(PostId.of(id));

        return new PostDto(newPost);
    }


}
