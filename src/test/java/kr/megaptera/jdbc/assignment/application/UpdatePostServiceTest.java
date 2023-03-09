package kr.megaptera.jdbc.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.UpdatePostRequest;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdatePostServiceTest {
    @Autowired
    private PostDao postDao;
    @Autowired
    private UpdatePostService updatePostService;


    @DisplayName("게시물 업데이트 테스트")
    @Test
    void updatePost() {
        // given
        String id = "1";
        Post oldPost = new Post(id, "제목", "Harry", "내용");
        postDao.save(oldPost);

        UpdatePostRequest request = new UpdatePostRequest("제목바뀜", "내용바뀜");

        // when
        updatePostService.updatePost(id, request);
        Post result = postDao.findById(id);

        // then
        assertThat(result.getId()).isEqualTo(oldPost.getId());
        assertThat(result.getTitle()).isEqualTo(request.getTitle());
        assertThat(result.getAuthor()).isEqualTo(oldPost.getAuthor());
        assertThat(result.getContent()).isEqualTo(request.getContent());

    }

    @AfterEach
    void tearDown() {
        postDao.clear();
    }
}
