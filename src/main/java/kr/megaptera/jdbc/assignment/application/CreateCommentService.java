package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.daos.CommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final CommentDao commentDao;

    public CommentDto addCommentDto(String postId, CommentDto commentDto) {
        commentDto.setPostId(postId);
        Comment comment = new Comment(PostId.of(commentDto.getPostId()), commentDto.getAuthor(), commentDto.getContent());
        commentDao.save(comment);
        return new CommentDto(comment);
    }

}
