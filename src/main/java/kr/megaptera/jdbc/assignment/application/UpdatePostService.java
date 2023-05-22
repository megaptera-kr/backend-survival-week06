package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class UpdatePostService {
    @Autowired
    private JdbcPostDao postDao;


    public void updatePost(String id, PostDto postUpdateDto) {
        Post post = postDao.find(PostId.of(id));

        post.update(
                postUpdateDto.getTitle(),
                MultilineText.of(postUpdateDto.getContent())
        );

        postDao.save(post);
    }
}
