package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDetailDto;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private Environment environment;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PostDao postDao;

    @SpyBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void list() throws Exception {
        for (int i = 0; i < 10; i++) {
            postDao.insert(new PostDetailDto(
                    PostId.generate().toString(), "Hello" + i, "ds" + i, "ddd" + i
            ));
        }

        mockMvc.perform(get("/posts")
                        .contentType("application/json"))
                .andDo(print());
        verify(postDao).selectAll();
        verify(postRepository).findAll();
    }

    @Test
    void detail() throws Exception {
        List<String> postIdList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            String postId;
            postDao.insert(new PostDetailDto(
                    postId = PostId.generate().toString(), "Hello" + i, "ds" + i, "ddd" + i
            ));
            postIdList.add(postId);
        }

        mockMvc.perform(get("/posts/" + postIdList.get(0))
                        .contentType("application/json"))
                .andDo(print());

        verify(postDao).selectById(argThat(it -> it.equals(postIdList.get(0))));
        verify(postRepository).findById(argThat(it -> it.toString().equals(postIdList.get(0))));
    }

    @Test
    void create() throws Exception {
        String json = """
                {
                  "author": "goeo1066",
                  "title": "Quantum Computing",
                  "content": "is near"
                }
                """;

        mockMvc.perform(post("/posts")
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(postDao).insert(argThat(it -> {
            return it.getAuthor().equals("goeo1066");
        }));
        verify(postRepository).save(argThat(it -> {
            return it.getAuthor().toString().equals("goeo1066");
        }));
    }

    @Test
    void update() throws Exception {
        List<String> postIdList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            String postId;
            postDao.insert(new PostDetailDto(
                    postId = PostId.generate().toString(), "Hello" + i, "ds" + i, "ddd" + i
            ));
            postIdList.add(postId);
        }
        String json = """
                {
                  "title": "Quantum Computing",
                  "content": "is near"
                }
                """;

        mockMvc.perform(patch("/posts/" + postIdList.get(0))
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isNoContent());

        verify(postDao).update(argThat(it -> {
            return it.getTitle().equals("Quantum Computing");
        }));
        verify(postRepository).save(argThat(it -> {
            return it.getTitle().toString().equals("Quantum Computing");
        }));
    }

    @Test
    void deletePost() throws Exception {
        List<String> postIdList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            String postId;
            postDao.insert(new PostDetailDto(
                    postId = PostId.generate().toString(), "Hello" + i, "ds" + i, "ddd" + i
            ));
            postIdList.add(postId);
        }
        mockMvc.perform(delete("/posts/" + postIdList.get(0))
                        .contentType("application/json"))
                .andExpect(status().isNoContent());

        verify(postDao).delete(argThat(it -> {
            return it.equals(postIdList.get(0));
        }));
        verify(postRepository).delete(argThat(it -> {
            return it.toString().equals(postIdList.get(0));
        }));
    }
}