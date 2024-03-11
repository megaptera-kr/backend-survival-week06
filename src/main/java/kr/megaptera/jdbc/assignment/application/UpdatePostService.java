package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final JdbcPostDao postDao;

    public UpdatePostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public void updatePost(String id, PostUpdateDto postUpdateDto) {
        Post post = postDao.find(PostId.of(id));

        post.update(
                postUpdateDto.getTitle(),
                MultilineText.of(postUpdateDto.getContent())
        );

        postDao.save(post);
    }
}
