package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.JdbcPostDao;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {

    private JdbcPostDao postDao;

    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postDao = mock(JdbcPostDao.class);

        getPostService = new GetPostService(postDao);
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() {
        given(postDao.find("1"))
                .willReturn(
                        new PostDto(
                                "1",
                                "공지사항입니다.",
                                "관리자",
                                "안녕하세요.\n자유게시판 이용 부탁드립니다.\n"));
        given(postDao.find("2"))
                .willReturn(
                        new PostDto(
                                "2",
                                "내가 첫 글???",
                                "김종희",
                                "신난닷!!\n너무 좋아용~~!!"));

        PostDto postDto = getPostService.getPost("2");
        assertThat(postDto).isNotNull();
        assertThat(postDto.getAuthor()).contains("종희");
        assertThat(postDto.getContent()).contains("좋아용");

        postDto = getPostService.getPost("1");
        assertThat(postDto).isNotNull();
        assertThat(postDto.getTitle()).contains("공지사항");

        assertThrows(PostNotFound.class, () -> getPostService.getPost("3"));
    }
}
