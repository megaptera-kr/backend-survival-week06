package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.applications.comment.*;
import kr.megaptera.jdbc.assignment.dtos.comment.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @DisplayName("GET 댓글 목록 얻기")
    void list() throws Exception {
        String postId = "ID_001";

        given(getCommentsService.getCommentDtos(postId)).willReturn(List.of(
                new CommentDto(
                        "ID_1",
                        postId,
                        "jyh",
                        "content_1"
                ),
                new CommentDto(
                        "ID_2",
                        postId,
                        "bjs",
                        "content_2"
                )
        ));

        mockMvc.perform(get("/comments?postId=" + postId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jyh")));
    }

    @Test
    @DisplayName("POST 댓글 생성")
    void create() throws Exception {
        String postId = "ID_001";
        String body = """
                {
                    "author": "jyh",
                    "content": "test_1"
                }
                """;

        mockMvc.perform(post("/comments?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());

        verify(createCommentService).create(eq(postId), any(CommentCreateDto.class));
    }

    @Test
    @DisplayName("PATCH 댓글 수정")
    void update() throws Exception {
        String commentId = "co_ID_001";
        String postId = "po_ID_001";
        String body = """
                {
                    "content": "test_1"
                }
                """;

        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent());

        verify(updateCommentService).updateComment(eq(commentId), eq(postId), any(CommentUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE 댓글 삭제")
    void deleteComment() throws Exception {
        String commentId = "co_ID_001";
        String postId = "po_ID_001";

        mockMvc.perform(delete("/comments/" + commentId + "?postId=" + postId))
                .andExpect(status().isNoContent());

        verify(deleteCommentService).delete(eq(commentId), eq(postId));
    }


}
