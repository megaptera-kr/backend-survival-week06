package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostDao postDao;

    public PostDto updatePostDto(String id, PostDto postDto) {
        Post post = postDao.find(PostId.of(id));
        post.update(postDto.getTitle(), postDto.getContent());
        postDao.update(post);
        return new PostDto(post);
    }

}
