package kr.megaptera.jdbc.assignment.controllers.posts;

import kr.megaptera.jdbc.assignment.application.posts.CreatePostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CreatePostService createPostService;

    @Test
    @DisplayName("POST /posts")
    void testCreatePost() throws Exception {
        String mockTitle = "제목";
        String mockAuthor = "작성자";
        String mockContent = "내용";

        String createPostJson = """
                {
                    "title": "%s",
                    "author": "%s",
                    "content": "%s"
                }
                """.formatted(mockTitle, mockAuthor, mockContent);

        RequestBuilder requestBuilder = post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createPostJson);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /posts")
    void testGetPosts() throws Exception {
        RequestBuilder requestBuilder = get("/posts");

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /posts/{postId}")
    void testGetPost() throws Exception {
        String mockPostId = "1";

        RequestBuilder requestBuilder = get("/posts/%s".formatted(mockPostId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /posts/{postId}")
    void testUpdatePost() throws Exception {
        String mockPostId = "1";

        RequestBuilder requestBuilder = put("/posts/%s".formatted(mockPostId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /posts/{postId}")
    void testDeletePost() throws Exception {
        String mockPostId = "1";

        RequestBuilder requestBuilder = delete("/posts/%s".formatted(mockPostId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}