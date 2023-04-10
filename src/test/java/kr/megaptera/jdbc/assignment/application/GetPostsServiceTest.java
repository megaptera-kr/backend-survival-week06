package kr.megaptera.jdbc.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostResponse;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetPostsServiceTest {

    @Autowired
    private PostDao postDao;

    @Autowired
    private GetPostsService getPostsService;

    @DisplayName("게시물 다수 조회 테스트")
    @Test
    void getPostList() {
        // given
        Post post1 = new Post("1", "제목1", "Harry", "내용1");
        Post post2 = new Post("2", "제목2", "Potter", "내용2");
        postDao.save(post1);
        postDao.save(post2);

        // when
        List<PostResponse> postResponses = getPostsService.getPostList();

        // then
        assertThat(postResponses.size()).isEqualTo(2);
    }

    @AfterEach
    void tearDown() {
        postDao.clear();
    }
}
