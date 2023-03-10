package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import kr.megaptera.jdbc.assignment.models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
                new PostDto(new Post("title1", "author1", "content1")),
                new PostDto(new Post("title2", "author2", "content2"))
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("title1")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        given(getPostService.getPostDto("001")).willReturn(
                new PostDto("001", "title1", "author1", "content1")
        );

        mockMvc.perform(get("/posts/001"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("title1")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detailWithIncorrectId() throws Exception {
        given(getPostService.getPostDto("001")).willThrow(PostNotFound.class);

        mockMvc.perform(get("/posts/001"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                    "title": "title1",
                    "author": "author1",
                    "content": "content1"
                }
                """;

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(createPostService).addPostDto(any(PostDto.class));
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        given(getPostService.getPostDto("001")).willReturn(
                new PostDto("001", "title1", "author1", "content1")
        );

        String json = """
                {
                    "title": "title2",
                    "content": "content2"
                }
                """;

        mockMvc.perform(patch("/posts/001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

        verify(updatePostService).updatePostDto(any(String.class), any(PostDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        given(getPostService.getPostDto("001")).willReturn(
                new PostDto("001", "title1", "author1", "content1")
        );

        mockMvc.perform(delete("/posts/001"))
                .andExpect(status().isNoContent());

        verify(deletePostService).removePostDto(any(String.class));
    }
}
