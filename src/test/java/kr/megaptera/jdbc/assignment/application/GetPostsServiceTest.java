package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
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
        postDao = mock(JdbcPostDao.class);
        getPostsService = new GetPostsService(postDao);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        given(postDao.findAll())
                .willReturn(
                        List.of(
                                new PostDto(
                                        "1",
                                        "공지사항입니다.",
                                        "관리자",
                                        "안녕하세요.\n자유게시판 이용 부탁드립니다.\n"),
                                new PostDto(
                                        "2",
                                        "내가 첫 글???",
                                        "김종희",
                                        "신난닷!!\n너무 좋아용~~!!")));

        List<PostDto> postDtos = getPostsService.getPosts();

        assertThat(postDtos.size()).isEqualTo(2);
        assertThat(postDtos.get(0).getTitle()).contains("공지사항");
        assertThat(postDtos.get(1).getContent()).contains("!!");
    }
}
