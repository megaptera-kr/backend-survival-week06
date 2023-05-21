package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.CreateCommentService;
import kr.megaptera.jdbc.assignment.application.DeleteCommentService;
import kr.megaptera.jdbc.assignment.application.GetCommentsService;
import kr.megaptera.jdbc.assignment.application.UpdateCommentService;
import kr.megaptera.jdbc.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.jdbc.assignment.domains.dto.CommentDto;
import kr.megaptera.jdbc.assignment.domains.dto.CommentUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetCommentsService getCommentsService;
    @MockBean
    CreateCommentService createCommentService;
    @MockBean
    UpdateCommentService updateCommentService;
    @MockBean
    DeleteCommentService deleteCommentService;


    @Test
    @DisplayName("GET /comments?postId={postId}  - with correct post ID")
    void list() throws Exception {
        given(getCommentsService.getComments("2"))
                .willReturn(
                        List.of(
                                new CommentDto("1", "관리자", "안녕하세요.\n자유게시판 이용 부탁드립니다.\n"),
                                new CommentDto("2", "김종희", "신난닷!!\n너무 좋아용~~!!")));

        mockMvc.perform(get("/comments?postId=2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString("자유게시판")))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString("김종희")));
    }

    @Test
    @DisplayName("GET /Comments?postId={postId} - If the post has no comments")
    void listWithNoComment() throws Exception {
        given(getCommentsService.getComments("2"))
                .willReturn(new ArrayList<>());

        mockMvc.perform(get("/comments?postId=2"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void create() throws Exception {
        String json = """
                {
                    "author": "케이",
                    "content": "하하하!"
                }
                """;

        mockMvc.perform(post("/comments?postId=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createCommentService).createComment(any(String.class), any(CommentCreateDto.class));
    }

    @Test
    @DisplayName("PATCH /Comments/{id}?postId={postId}")
    void update() throws Exception {
        given(updateCommentService.updateComment("1", "3", new CommentUpdateDto("하하하!")))
                .willReturn(new CommentDto("1", "관리자", "하하하!"));

        String json = """
                {
                    "content": "하하하!"
                }
                """;

        mockMvc.perform(patch("/comments/1?postId=3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(updateCommentService).updateComment(any(String.class), any(String.class), any(CommentUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /Comments/{id}?postId={postId}")
    void deleteComment() throws Exception {
        given(deleteCommentService.deleteComment("1", "3"))
                .willReturn(new CommentDto("1", "관리자", "하하하!"));

        mockMvc.perform(delete("/comments/1?postId=3")).andExpect(status().isOk());

        verify(deleteCommentService).deleteComment(any(String.class), any(String.class));
    }

}
