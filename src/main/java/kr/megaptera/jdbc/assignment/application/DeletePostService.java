package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final PostDao postDao;

    public PostDto removePostDto(String id) {
        Post post = postDao.find(PostId.of(id));
        postDao.delete(PostId.of(id));
        return new PostDto(post);
    }

}
