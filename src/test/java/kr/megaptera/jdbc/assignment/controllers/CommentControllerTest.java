package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.PostId;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    // TODO: PostControllerTest 를 참고해서 테스트를 작성해 주세요.
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCommentsService getCommentsService;

    @MockBean
    private CreateCommentService createCommentService;

    @MockBean
    private UpdateCommentService updateCommentService;

    @MockBean
    private DeleteCommentService deleteCommentService;

    @Test
    @DisplayName("GET /comments?postId={postId}")
    void list() throws Exception {
        given(getCommentsService.getCommentDtos("001")).willReturn(List.of(
                new CommentDto(new Comment(PostId.of("001"), "author1", "content1")),
                new CommentDto(new Comment(PostId.of("001"), "author2", "content2"))
        ));

        mockMvc.perform(get("/comments?postId=001"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("content1")
                ));
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void create() throws Exception {
        String json = """
                {
                    "author": "author1",
                    "content": "content1"
                }
                """;

        mockMvc.perform(post("/comments?postId=001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(createCommentService).addCommentDto(any(String.class), any(CommentDto.class));
    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void update() throws Exception {
        given(getCommentsService.getCommentDtos("001")).willReturn(List.of(
                new CommentDto("001", "001", "author1", "content1")
        ));

        String json = """
                {
                    "content": "content2"
                }
                """;

        mockMvc.perform(patch("/comments/001?postId=001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

        verify(updateCommentService).updateCommentDto(any(String.class), any(String.class), any(CommentDto.class));
    }

    @Test
    @DisplayName("DELETE /comments/{id}?postId={postId}")
    void deleteComment() throws Exception {
        given(getCommentsService.getCommentDtos("001")).willReturn(List.of(
                new CommentDto("001", "001", "author1", "content1")
        ));

        mockMvc.perform(delete("/comments/001"))
                .andExpect(status().isNoContent());

        verify(deleteCommentService).removeCommentDto(any(String.class));
    }
}
