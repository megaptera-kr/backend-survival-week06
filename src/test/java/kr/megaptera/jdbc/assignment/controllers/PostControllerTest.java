package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.applications.post.*;
import kr.megaptera.jdbc.assignment.dtos.post.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private UpdatePostService updatePostService;

    @MockBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("GET 게시글 목록 얻기")
    void list() throws Exception {
        given(getPostsService.findAll()).willReturn(List.of(
                new PostDto("test_1", "title_1", "jyh", "content_1"),
                new PostDto("test_2", "title_2", "bjs", "content_2")
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("jyh")
                ));
    }

    @Test
    @DisplayName("GET 게시글 얻기")
    void detail() throws Exception {
        String id = "id_001";
        given(getPostService.find(id)).willReturn(new PostDto(id, "title_1", "jyh", "content_1"));

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(id)));
    }

    @Test
    @DisplayName("GET 존재하지 않는 게시글 얻기")
    void detailNotFound() throws Exception {
        String id = "id_001";

        given(getPostService.find(id)).willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST 게시글 생성")
    void create() throws Exception {
        String json = """
                {
                    "title": "test_1",
                    "author": "jyh",
                    "content": "content_test_1"
                }
                """;

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createPostService).create(any(PostCreateDto.class));
    }

    @Test
    @DisplayName("PATCH 게시글 수정")
    void update() throws Exception {
        String body = """
                {
                    "title": "changed_1",
                    "content": "content_test_1"
                }
                """;
        String postId = "ID_001";

        mockMvc.perform(patch("/posts/" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        verify(updatePostService).update(eq(postId), any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE 게시글 삭제")
    void deletePost() throws Exception {
        String postId = "ID_001";

        mockMvc.perform(delete("/posts/" + postId))
                .andExpect(status().isOk());

        verify(deletePostService).delete(eq(postId));
    }
}
