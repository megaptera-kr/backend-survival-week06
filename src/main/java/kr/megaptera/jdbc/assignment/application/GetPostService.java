package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class GetPostService {

    private final JdbcPostDao postDao;

    public GetPostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public PostDto getPostDto(String id) {
        Post post = postDao.find(PostId.of(id));

        return new PostDto(post);
    }
}
