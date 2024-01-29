package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetCommentsService {

    private final CommentDao commentDao;

    public List<CommentDto> getCommentList(String postId) {
        log.info("postId: {}", postId);
        List<Comment> comments = commentDao.findAll(postId);

        return comments.stream().map(CommentDto::new).toList();
    }
}
