package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.applications.*;
import kr.megaptera.jdbc.assignment.dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    void list() throws Exception {
//        String postId = "001";
//        given(commentService.getList(postId)).willReturn(List.of(
//                new CommentDto("001COMMENT", "작성자1", "댓글내용1"),
//                new CommentDto("001COMMENT", "작성자2", "댓글내용2")
//        ));
//
//        mockMvc.perform(get("/comment?postId=" + postId))
//                .andExpect(status().isOk())
//                .andExpect(content().string(
//                        containsString("댓글내용1")
//                ));
    }

    @Test
    void create() throws Exception {
        String postId = "001";

        String json = """
                {
                  "author": "me",
                  "content": "wow"
                }
                """;

        mockMvc.perform(post("/comments?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        verify(commentService).createComment(any(), any(CommentCreateDto.class));
    }

    @Test
    void update() throws Exception {
//        String commentId = "001COMMENT";
//        String postId = "001POST";
//
//        String json = """
//                {
//                  "content": "댓글 입니다."
//                }
//                """;
//        mockMvc.perform(patch("/comments" + commentId + "?postId" + postId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json)).andExpect(status().isNoContent());
//
//        verify(commentService).updateComment(eq(postId),
//                any(CommentUpdateDto.class), eq(commentId));
    }

    @Test
    void delete() {

    }

}