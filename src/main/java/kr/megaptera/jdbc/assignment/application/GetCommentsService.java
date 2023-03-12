package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcCommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetCommentsService {
    private final JdbcCommentDao jdbcCommentDao;

    public GetCommentsService(JdbcCommentDao jdbcCommentDao) {
        this.jdbcCommentDao = jdbcCommentDao;
    }

    public List<CommentDto> getCommentDto(String postId) {
        List<CommentDto> commentDtos = new ArrayList<>();

        List<Comment> comments = jdbcCommentDao.findAll(PostId.of(postId));

        if (comments.size() > 0) {
            comments.stream().forEach(i -> {
                CommentDto cmtDto = new CommentDto(i.id().toString(), i.author(), i.content().toString());
                commentDtos.add(cmtDto);
            });
        }

        return commentDtos;
    }

}
