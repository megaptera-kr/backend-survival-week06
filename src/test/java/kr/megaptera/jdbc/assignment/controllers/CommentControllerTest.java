package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentEntityDto;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CommentDao commentDao;

    @SpyBean
    private CommentRepository commentRepository;

    @Test
    void list() throws Exception {
        List<String> postIdList = new ArrayList<>(2);
        Map<String, List<String>> postIdCommentIdMap = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            String postId = PostId.generate().toString();
            postIdList.add(postId);
            postIdCommentIdMap.put(postId, new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                String commentId = CommentId.generate().toString();
                postIdCommentIdMap.get(postId).add(commentId);
            }
        }

        mockMvc.perform(get("/comments?postId=" + postIdList.get(0))
                        .content("application/json"))
                .andExpect(status().isOk());

        verify(commentDao).selectAllByPostId(argThat(it -> {
            return it.equals(postIdList.get(0));
        }));
        verify(commentRepository).findAllByPostId(argThat(it -> {
            return it.toString().equals(postIdList.get(0));
        }));
    }

    @Test
    void create() throws Exception {
        String json = """
                {
                    "author": "goeo1066",
                    "content": "so fun"
                }
                """;
        String postId = PostId.generate().toString();
        mockMvc.perform(post("/comments?postId=" + postId)
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isCreated());
        verify(commentDao).insert(argThat(it -> {
            return it.getPostId().equals(postId) && it.getAuthor().equals("goeo1066");
        }));
        verify(commentRepository).save(argThat(it -> {
            return it.getPostId().toString().equals(postId);
        }));
    }

    @Test
    void update() throws Exception {
        String commentId = CommentId.generate().toString();
        String postId = PostId.generate().toString();
        commentDao.insert(new CommentEntityDto(commentId, postId, "goeo1066", "so fun"));
        String json = """
                {
                    "content": "so fun again"
                }
                """;
        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + postId)
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isNoContent());
        verify(commentDao).update(argThat(it -> {
            return it.getContent().equals("so fun again");
        }));
        verify(commentRepository).save(argThat(it -> {
            return it.getContent().toString().equals("so fun again");
        }));
    }

    @Test
    void deleteComment() throws Exception {
        String commentId = CommentId.generate().toString();
        String postId = PostId.generate().toString();
        commentDao.insert(new CommentEntityDto(commentId, postId, "goeo1066", "so fun"));

        mockMvc.perform(delete("/comments/" + commentId + "?postId=" + postId)
                        .contentType("application/json"))
                .andExpect(status().isNoContent());

        verify(commentDao).delete(
                argThat(it -> it.equals(commentId)),
                argThat(it -> it.equals(postId))
        );
        verify(commentRepository).delete(
                argThat(it -> it.toString().equals(commentId)),
                argThat(it -> it.toString().equals(postId))
        );
    }
}