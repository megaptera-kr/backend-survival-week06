package kr.megaptera.jdbc.assignment.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
public class JdbcCommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;

    @DisplayName("댓글 저장 테스트")
    @Test
    void saveSuccess() {
        // given
        String postId = "1";
        Post post = new Post(postId, "제목", "Harry", "내용");
        Comment comment = new Comment(postId, "Potter", "좋아요");
        postDao.save(post);

        // when
        Comment result = commentDao.save(comment);

        // then
        assertThat(result.getId()).isEqualTo(comment.getId());
        assertThat(result.getPostId()).isEqualTo(comment.getPostId());
        assertThat(result.getAuthor()).isEqualTo(comment.getAuthor());
        assertThat(result.getContent()).isEqualTo(comment.getContent());
    }

    @DisplayName("한 게시물에 대한 댓글 다수 가져오기 테스트")
    @Test
    void getCommentsByPostId() {
        // given
        String postId = "1";
        Post post = new Post(postId, "제목", "Harry", "내용");
        Comment comment1 = new Comment(postId, "Potter", "좋아요");
        Comment comment2 = new Comment(postId, "Ron", "저도 좋아요");

        postDao.save(post);
        commentDao.save(comment1);
        commentDao.save(comment2);

        // when
        List<Comment> comments = commentDao.findByPostId(postId);

        // then
        assertThat(comments.size()).isEqualTo(2);
    }

    @DisplayName("댓글 수정 테스트")
    @Test
    void updateComment() {
        // given
        String postId = "1";
        Post post = new Post(postId, "제목", "Harry", "내용");
        Comment comment = new Comment(postId, "Potter", "좋아요");

        postDao.save(post);
        commentDao.save(comment);

        // when
        commentDao.update(comment.getId(), postId, "댓글내용바뀜");
        Comment result = commentDao.find(comment.getId(), postId);

        // then
        assertThat(result.getId()).isEqualTo(comment.getId());
        assertThat(result.getPostId()).isEqualTo(postId);
        assertThat(result.getAuthor()).isEqualTo(comment.getAuthor());
        assertThat(result.getContent()).isEqualTo("댓글내용바뀜");
    }

    @DisplayName("댓글 삭제 테스트")
    @Test
    void deleteComment() {
        // given
        String postId = "1";
        Post post = new Post(postId, "제목", "Harry", "내용");
        Comment comment = new Comment(postId, "Potter", "좋아요");

        postDao.save(post);
        commentDao.save(comment);

        // when
        commentDao.delete(comment.getId(), postId);

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
