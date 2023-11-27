package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {

    private PostDao postDao;

    @Autowired
    public UpdatePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto updatePostDto(String id, PostDto postDto) {
        Post post = postDao.find(PostId.of(id));

        if (post == null) {
            throw new PostNotFound();
        }
        post.update(PostTitle.of(postDto.getTitle()), MultilineText.of(postDto.getContent()));
        postDao.update(post);
        return new PostDto(post);
    }
}
