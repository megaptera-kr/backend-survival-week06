package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GetPostsService {
    private final JdbcPostDao postDao;

    public GetPostsService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = postDao.findAll();

        return posts.stream().map(PostDto::new).toList();
    }
}
