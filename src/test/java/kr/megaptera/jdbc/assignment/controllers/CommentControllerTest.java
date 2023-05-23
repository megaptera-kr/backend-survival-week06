package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.dtos.*;
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

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    MockMvc mockMvc;

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
        String postId = "001POST";
        given(getCommentsService.getCommentDtos(postId)).willReturn(List.of(
            new CommentDto("001COMMENT", "작성자1", "댓글내용1"),
            new CommentDto("001COMMENT", "작성자2", "댓글내용2")
        ));

        mockMvc.perform(get("/comments?postId=" + postId))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("댓글내용1")
            ));
    }

    @Test
    @DisplayName("POST /comments")
    void create() throws Exception {
        String postId = "001POST";

        String json = """
                {
                  "author": "노아",
                  "content": "댓글 씁시다."
                }
                """;

        mockMvc.perform(post("/comments?postId=" + postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andExpect(status().isCreated());

        verify(createCommentService).create(
            any(),
            any(CommentDto.class)
        );
    }

    @Test
    @DisplayName("PATCH /comments/{id}")
    void update() throws Exception {
        String commentId = "001COMMENT";
        String postId = "001POST";

        String json = """
                {
                  "content": "댓글 입니다."
                }
                """;

        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andExpect(status().isNoContent());

        verify(updateCommentService)
            .updateComment(eq(commentId),
                eq(postId),
                any(CommentDto.class)
            );
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String commentId = "001COMMENT";
        String postId = "001POST";

        mockMvc.perform(delete("/comments/" + commentId + "?postId=" + postId))
            .andExpect(status().isNoContent());

        verify(deleteCommentService).deleteComment(commentId, postId);
    }
}
