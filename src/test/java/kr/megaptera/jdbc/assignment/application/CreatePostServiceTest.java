package kr.megaptera.jdbc.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.CreatePostRequest;
import kr.megaptera.jdbc.assignment.dtos.PostResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreatePostServiceTest {

    @Autowired
    private PostDao postDao;
    @Autowired
    private CreatePostService createPostService;


    @DisplayName("게시물 저장 테스트")
    @Test
    void createPost() {
        // given
        CreatePostRequest request = new CreatePostRequest("제목", "Harry", "내용");

        // when
        PostResponse result = createPostService.createPost(request.toPost());

        // then
        assertThat(result.getTitle()).isEqualTo(request.getTitle());
        assertThat(result.getAuthor()).isEqualTo(request.getAuthor());
        assertThat(result.getContent()).isEqualTo(request.getContent());
    }

    @AfterEach
    void tearDown() {
        postDao.clear();
    }
}
