package kr.megaptera.jdbc.assignment.application;

import static org.assertj.core.api.Assertions.assertThatCode;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
public class DeleteCommentServiceTest {
    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private DeleteCommentService deleteCommentService;

    @DisplayName("댓글 삭제하기 테스트")
    @Test
    void deleteComment() {
        // given
        String postId = "1";
        Post post = new Post(postId, "제목", "Harry", "내용");
        Comment comment = new Comment(postId, "Potter", "좋아요");

        postDao.save(post);
        commentDao.save(comment);

        // when
        deleteCommentService.deleteComment(comment.getId(), postId);

        // then
        assertThatCode(() -> commentDao.find(comment.getId(), postId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @AfterEach
    void tearDown() {
        commentDao.clear();
        postDao.clear();
    }
}
