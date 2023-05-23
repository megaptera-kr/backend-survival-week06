package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {

    private final PostDao postDao;

    public GetPostsService(PostDao postDao) {
        this.postDao = postDao;
    }

    public ResponseEntity<List<PostDto>> getPostList() {
        List<Post> postList = postDao.findAll();

        List<PostDto> postDtoList = postList.stream()
                .map(PostDto::new)
                .toList();

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }
}
