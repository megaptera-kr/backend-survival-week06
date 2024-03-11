package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.post.CreatePostService;
import kr.megaptera.jdbc.assignment.application.post.DeletePostServcie;
import kr.megaptera.jdbc.assignment.application.post.GetPostService;
import kr.megaptera.jdbc.assignment.application.post.GetPostsService;
import kr.megaptera.jdbc.assignment.application.post.UpdatePostService;
import kr.megaptera.jdbc.assignment.dtos.post.GetPostDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetPostService getPostService;
    @MockBean
    private GetPostsService getPostsService;
    @MockBean
    private CreatePostService createPostService;
    @MockBean
    private UpdatePostService updatePostService;
    @MockBean
    private DeletePostServcie deletePostServcie;

    @Test
    @DisplayName("GET /posts/{id} - with valid id")
    void getPost() throws Exception {
        // given
        String postId = "1";

        given(getPostService.getPost(postId)).willReturn(new GetPostDTO("1", "제목", "작가", "내용"));

        // when
        // then
        mockMvc.perform(get("/posts/" + postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.author").value("작가"))
                .andExpect(jsonPath("$.content").value("내용"));
    }
}
