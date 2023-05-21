package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreatePostService;
import kr.megaptera.jdbc.assignment.application.DeletePostService;
import kr.megaptera.jdbc.assignment.application.GetPostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.application.UpdatePostService;
import kr.megaptera.jdbc.assignment.domains.dto.PostCreateDto;
import kr.megaptera.jdbc.assignment.domains.dto.PostDto;
import kr.megaptera.jdbc.assignment.domains.dto.PostUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetPostService getPostService;
    @MockBean
    GetPostsService getPostsService;
    @MockBean
    CreatePostService createPostService;
    @MockBean
    UpdatePostService updatePostService;
    @MockBean
    DeletePostService deletePostService;

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        given(getPostsService.getPosts())
                .willReturn(
                        List.of(
                                new PostDto("1", "공지사항입니다.", "관리자", "안녕하세요.\n자유게시판 이용 부탁드립니다.\n"),
                                new PostDto("2", "내가 첫 글???", "김종희", "신난닷!!\n너무 좋아용~~!!")));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString("공지사항")));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        given(getPostService.getPost("1"))
                .willReturn(new PostDto("1", "공지사항입니다.", "관리자", "안녕하세요.\n자유게시판 이용 부탁드립니다.\n"));

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString("관리자")));


    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        given(getPostService.getPost("2"))
                .willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                    "author": "케이",
                    "title": "새 글",
                    "content": "하하하!"
                }
                """;

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createPostService).createPost(any(PostCreateDto.class));
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        given(updatePostService.updatePost("1", new PostUpdateDto("새 글", "하하하!")))
                .willReturn(new PostDto("1", "새 글", "관리자", "하하하!"));

        String json = """
                {
                    "title": "새 글",
                    "content": "하하하!"
                }
                """;

        mockMvc.perform(patch("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(updatePostService).updatePost(any(String.class), any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        given(deletePostService.deletePost("1"))
                .willReturn(new PostDto("1", "새 글", "관리자", "하하하!"));

        mockMvc.perform(delete("/posts/1")).andExpect(status().isOk());

        verify(deletePostService).deletePost(any(String.class));
    }
}
