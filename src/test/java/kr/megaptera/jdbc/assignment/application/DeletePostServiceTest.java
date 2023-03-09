package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
public class DeletePostServiceTest {
    @Autowired
    private PostDao postDao;

    @Autowired
    private DeletePostService deletePostService;

    @DisplayName("게시물 삭제 테스트")
    @Test
    void deletePost() {
        // given
        Post post = new Post("1", "제목", "Harry", "내용");
        postDao.save(post);

        // when
        deletePostService.deletePost(post.getId());

        // then
        assertThatCode(()->postDao.findById(post.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @AfterEach
    void tearDown() {
        postDao.clear();
    }
}
