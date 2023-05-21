package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.domains.dto.PostUpdateDto;
import kr.megaptera.jdbc.assignment.domains.model.Post;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {

    private final JdbcPostDao postDao;

    public UpdatePostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto updatePost(String id, PostUpdateDto postUpdateDto) {
        PostDto postDto = postDao.find(id);

        if (postDto == null)
            throw new PostNotFound();

        Post post = new Post(postDto);
        post.updatePost(postUpdateDto);
        postDao.save(post);
        return new PostDto(post);
    }
}
