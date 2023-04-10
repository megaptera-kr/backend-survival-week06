package kr.megaptera.jdbc.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponse;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetPostServiceTest {

    @Autowired
    private PostDao postDao;

    @Autowired
    private GetPostService getPostService;


    @DisplayName("게시물 단건 조회 테스트")
    @Test
    void getPostSuccess() {
        // given
        Post expected = new Post("1", "제목", "Harry", "내용");
        postDao.save(expected);

        // when
        PostResponse result = getPostService.getPost("1");

        // then
        assertThat(result.getId()).isEqualTo(expected.getId());
        assertThat(result.getTitle()).isEqualTo(expected.getTitle());
        assertThat(result.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(result.getContent()).isEqualTo(expected.getContent());
    }

    @AfterEach
    void tearDown() {
        postDao.clear();
    }
}
