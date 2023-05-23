package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {

    private final PostDao postDao;

    public DeletePostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public ResponseEntity<PostDto> deletePost(int postId) {
        Post post = postDao.findById(PostId.of(postId));
        postDao.deletePost(post);
        PostDto postDto = new PostDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
