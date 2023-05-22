package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import kr.megaptera.jdbc.assignment.repository.*;
import org.springframework.stereotype.*;

@Service
public class DeletePostService {
    private final JdbcPostDao postDao;


    public DeletePostService(JdbcPostDao postDao) {
        this.postDao = postDao;
    }

    public void deletePost(String id) {
        postDao.delete(PostId.of(id));
    }
}
