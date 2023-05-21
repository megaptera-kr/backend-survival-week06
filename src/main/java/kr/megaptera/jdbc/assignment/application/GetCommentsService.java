package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
    private final CommentDao commentDao;

    public GetCommentsService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }


    public List<CommentDto> getComments(String postId) {
        List<Comment> comments = commentDao.findAll(PostId.of(postId));
        System.out.println("service findall comments" + comments.toString());
        List<CommentDto> dtoList = comments.stream().map(CommentDto::new).toList();
        System.out.println("service dtolist comments" + dtoList.toString());
        return dtoList;
    }
}
