package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreatePostService;
import kr.megaptera.jdbc.assignment.application.DeletePostService;
import kr.megaptera.jdbc.assignment.application.GetPostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.application.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {
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
        given(getPostsService.getList()).willReturn(List.of(new PostDto("1", "제목", "작성자", "글 내용")));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("제목")));
    }

    @Test
    @DisplayName("findOne")
    void detail() throws Exception {
        String id = "1";
        given(getPostService.getPost(id)).willReturn(new PostDto("1", "test", "test", "test"));

        mockMvc.perform(get("/posts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));

        PostDto postDto = getPostService.getPost(id);

        assertThat(postDto).isNotNull();
        assertThat(postDto.getId()).isEqualTo(id);
        assertThat(postDto.getAuthor()).isEqualTo("test");
    }

    @Test
    @DisplayName("no id")
    void incorrect() throws Exception {
        String id = "1000";
        given(getPostService.getPost(id)).willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                    "title" : "새 글",
                    "author" : "작성자",
                    "content" : "내용"
                }
                """;
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

    }


    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {

        String id = "1";
        String json = """
                {
                    "title" : "팔각모",
                    "content" : "사나이"
                }
                """;

        mockMvc.perform(patch("/posts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk());

        verify(updatePostService).updatePost(eq(id), any(PostDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "100";

        mockMvc.perform(delete("/posts/{id}", id))
                .andExpect(status().isOk());

        verify(deletePostService).deletePost(id);
    }


}
