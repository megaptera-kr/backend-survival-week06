package kr.megaptera.jdbc.assignment.controller;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.controllers.CommentController;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {
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
                new CommentDto("002COMMENT", "작성자2", "댓글내용2")
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
                 "author": "철현",
                 "content": "댓글 씁시다."
                }
                """;

        mockMvc.perform(post("/comments?postId=" + postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                )
                .andExpect(status().isCreated());

        verify(createCommentService).createComment(any(), any(CommentCreateDto.class));
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
                        .updateComment(eq(commentId), eq(postId), any(CommentUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String commentId = "001COMMENT";
        String postId = "001POST";

        mockMvc.perform(delete("/comments/" + commentId + "?postId=" + postId))
                .andExpect(status().isNoContent());

        verify(deleteCommentService).delete(commentId, postId);
     }
}
