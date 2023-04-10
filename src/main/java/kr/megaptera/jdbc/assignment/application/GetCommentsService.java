package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommentsService {

    private final CommentDao commentDao;

    public List<CommentDto> getCommentDtos(String postId) {
        List<Comment> comments = commentDao.findByPostId(postId);
        return comments.stream().map(comment -> new CommentDto(comment)).toList();
    }

}
