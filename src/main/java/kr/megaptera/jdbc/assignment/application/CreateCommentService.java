package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final CommentDao commentDao;

    public CommentDto createComment(String postId, CommentDto commentDto) {
        Comment comment = Comment.builder()
                .postId(postId)
                .author(commentDto.getAuthor())
                .content(commentDto.getContent())
                .build();
        commentDao.createComment(comment);

        return new CommentDto(comment);
    }
}
