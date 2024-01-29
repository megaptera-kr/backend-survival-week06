package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.domain.Post;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostsServiceTest {
    private JdbcPostDao postDao;

    private GetPostsService getPostsService;

    @BeforeEach
    void setUp() {
        //모의 객체 생성
        postDao = mock(JdbcPostDao.class);

        getPostsService = new GetPostsService(postDao);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        // given - 모의 객체에 대한 행동을 정의
        given(postDao.findAll()).willReturn(List.of(
                Post.builder()
                        .id("1")
                        .title("title")
                        .content("content")
                        .author("author")
                        .build()
        ));
        // when - 테스트할 로직 실행
        List<PostDto> postDtos = getPostsService.getPostList();
        // then - 테스트 결과 검증
        assertThat(postDtos).hasSize(1);
    }
}
