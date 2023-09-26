package kr.megaptera.jdbc.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.jdbc.assignment.application.CreatePostService;
import kr.megaptera.jdbc.assignment.application.DeletePostService;
import kr.megaptera.jdbc.assignment.application.GetPostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.application.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;


@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
        given(getPostsService.getPostsDto()).willReturn(List.of(
                new PostDto("001POST", "제목1", "작성자1", "본문1"),
                new PostDto("002POST", "제목2", "작성자2", "본문2")
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("본문1")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrext() throws Exception {
        String id = "001POST";
        given(getPostService.getPostDto(id)).willReturn(
                new PostDto(id, "제목", "작성자", "본문")
        );

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("제목")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detail() throws Exception {
        String id = "001POST";
        given(getPostService.getPostDto(id))
                .willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String id = "001POST";

        String json = """
                {
                "title": "테스트",
                "author": "나",
                "content": "본문 테스트~"
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
        String id = "001POST";

        String json = """
                {
                "title": "테스트",
                "content": "본문 테스트~~~~"
                }
                """;

        mockMvc.perform(patch("/posts/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

        verify(updatePostService).updatePost(eq(id), any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "001POST";

        mockMvc.perform(delete("/posts/"+id))
                .andExpect(status().isNoContent());

        verify(deletePostService).deletePost(id);
    }


}
