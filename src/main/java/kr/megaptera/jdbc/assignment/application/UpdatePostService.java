package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.domain.Post;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostDao postDao;

    public PostDto updatePost(String id, PostDto postDto) throws PostNotFound {
        Post post = postDao.find(id);
        post.update(postDto.getTitle(), postDto.getContent());
        postDao.updatePostsave(post);
        return new PostDto(post);
    }
}
