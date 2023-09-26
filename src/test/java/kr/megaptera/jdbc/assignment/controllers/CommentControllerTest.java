package kr.megaptera.jdbc.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
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

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
                  "author": "작성자1",
                  "content": "댓글 내용1"
                }
                """;

        mockMvc.perform(post("/comments?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(createCommentService).createComment(
                any(),
                any(CommentCreateDto.class)
        );
    }

    @Test
    @DisplayName("PATCH /comments/{id}")
    void update() throws Exception {
        String commentId = "001COMMENT";
        String postId = "001POST";

        String json = """
                {
                  "content": "댓글 내용."
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
                        any(CommentUpdateDto.class)
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
