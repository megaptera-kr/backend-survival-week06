package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.dtos.request.RqUpdatePostDto;
import kr.megaptera.jdbc.assignment.models.Content;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.Title;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
 public class UpdatePostService {

    private final PostDao postDao;

    public UpdatePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public ResponseEntity<PostDto> updatePost(RqUpdatePostDto dto, int postId) {
        Post post = postDao.findById(PostId.of(postId));
        post.update(Title.of(dto.getTitle()),
                Content.of(dto.getContent()));
        Post updatePost = postDao.updatePost(post);
        PostDto postDto = new PostDto(updatePost);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
