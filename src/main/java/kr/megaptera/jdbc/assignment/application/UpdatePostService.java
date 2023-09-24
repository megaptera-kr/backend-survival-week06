package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {
    private final JdbcPostDao postDao;

    public void updatePost(String id, PostUpdateDto postUpdateDto) {
        Post post = postDao.find(PostId.of(id));

        post.update(postUpdateDto.getTitle(),
                MultilineText.of(postUpdateDto.getContent()));

        postDao.update(post);
    }
}
