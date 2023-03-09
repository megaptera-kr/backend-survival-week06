package kr.megaptera.jdbc.assignment.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
public class JdbcPostDaoTest {
    @Autowired
    private PostDao postDao;

    @DisplayName("게시물 저장 테스트")
    @Test
    void saveSuccess() {
        // given
        Post expected = new Post("1", "제목", "Harry", "내용");

        // when
        Post result = postDao.save(expected);

        // then
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getTitle()).isEqualTo(expected.getTitle());
        assertThat(result.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(result.getContent()).isEqualTo(expected.getContent());
    }

    @DisplayName("게시물 다수 가져오기 테스트")
    @Test
    void getPostList() {
        // given
        Post post1 = new Post("제목1", "Harry", "내용1");
        Post post2 = new Post("제목2", "Harry", "내용2");
        postDao.save(post1);
        postDao.save(post2);

        // when
        List<Post> results = postDao.findAll();

        // then
        assertThat(results.size()).isEqualTo(2);
    }

    @DisplayName("게시물 단건 가져오기 테스트")
    @Test
    void getPostSuccess() {
        // given
        Post expected = new Post("1", "제목", "Harry", "내용");
        postDao.save(expected);

        // when
        Post result = postDao.findById("1");

        // then
        assertThat(result.getId()).isEqualTo(expected.getId());
        assertThat(result.getTitle()).isEqualTo(expected.getTitle());
        assertThat(result.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(result.getContent()).isEqualTo(expected.getContent());
    }

    @DisplayName("게시물이 없으면 익셉션이 발생한다.")
    @Test
    void getPostFail() {
        assertThatCode(()-> postDao.findById("1")).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("게시물 업데이트 테스트")
    @Test
    void updatePost() {
        // given
        Post oldPost = new Post("1", "제목", "Harry", "내용");
        postDao.save(oldPost);

        // when
        postDao.update("1", "제목바뀜", "내용바뀜");

        // then
        Post result = postDao.findById("1");
        assertThat(result.getTitle()).isEqualTo("제목바뀜");
        assertThat(result.getContent()).isEqualTo("내용바뀜");
    }

    @DisplayName("게시물 삭제 테스트")
    @Test
    void deletePost() {
        // given
        Post expected = new Post("제목", "Harry", "내용");
        postDao.save(expected);

        // when
        postDao.delete("1");

        // then
        assertThatCode(()-> postDao.findById("1")).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @AfterEach
    void tearDown() {
        postDao.clear();
    }
}
