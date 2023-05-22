package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import kr.megaptera.jdbc.assignment.exceptions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {
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
    @DisplayName("GET /posts")
    void list() throws Exception {
        given(getPostsService.getPostDtos()).willReturn(List.of(
                new PostDto("001", "제목1", "작성자1", "내용1"),
                new PostDto("002", "제목2", "작성자2", "내용2")
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("제목1")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        String id = "001POST";

        given(getPostService.getPostDto(id))
                .willReturn(new PostDto(id, "제목", "작성자", "내용"));

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("제목")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        String id = "999POST";

        given(getPostService.getPostDto(id))
                .willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                  "title": "데브로드",
                  "author": "홀맨",
                  "content": "열심히 합시다"
                }
                """;

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(createPostService).createPost(any(PostDto.class));
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        String id = "001POST";

        String json = """
                {
                  "title": "데브로드",
                  "content": "열심히 합시다"
                }
                """;

        mockMvc.perform(patch("/posts/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isNoContent());

        verify(updatePostService)
                .updatePost(eq(id), any(PostDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "001POST";

        mockMvc.perform(delete("/posts/" + id))
                .andExpect(status().isNoContent());

        verify(deletePostService).deletePost(id);
    }
}
