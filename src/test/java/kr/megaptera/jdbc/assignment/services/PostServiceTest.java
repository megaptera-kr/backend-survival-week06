package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostCreateDto;
import kr.megaptera.jdbc.assignment.dtos.PostDetailDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateDto;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PostServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @SpyBean
    private PostDao postDao;

    @SpyBean
    private PostRepository postRepository;

    @Test
    void list() throws Exception {
        for (int i = 0; i < 10; i++) {
            postDao.insert(new PostDetailDto(
                    PostId.generate().toString(), "Hello" + i, "ds" + i, "ddd" + i
            ));
        }

        List<PostDetailDto> dtoList = postService.list();
        System.out.println(dtoList.size());
        verify(postRepository).findAll();
        verify(postDao).selectAll();
    }

    @Test
    void detail() throws Exception {
        String postId = PostId.generate().toString();
        postDao.insert(new PostDetailDto(postId, "goeo1066", "the optical computing", "looks good"));
        PostDetailDto dto = postService.detail(postId);
        assertThat(dto).satisfies(it -> {
            assertThat(it.getAuthor()).isEqualTo("goeo1066");
            assertThat(it.getTitle()).contains("optical");
            assertThat(it.getContent()).contains("looks good");
        });
        verify(postRepository).findById(argThat(it -> it.toString().equals(postId)));
        verify(postDao).selectById(argThat(it -> it.equals(postId)));
    }

    @Test
    void create() throws Exception {
        PostCreateDto dto = new PostCreateDto("goeo1066", "Make Korea Great Again", "yes");
        PostDetailDto postDetailDto = postService.create(dto);
        assertThat(postDetailDto).satisfies(it -> {
            assertThat(it.getAuthor()).isEqualTo("goeo1066");
        });
        verify(postRepository).save(argThat(it -> it.getAuthor().toString().equals("goeo1066")));
        verify(postDao).insert(argThat(it -> it.getAuthor().equals("goeo1066")));
    }

    @Test
    void update() throws Exception {
        String postId = PostId.generate().toString();
        postDao.insert(new PostDetailDto(postId, "goeo1066", "the optical computing", "looks good"));
        PostUpdateDto dto = new PostUpdateDto("the practical approach", "looks so good");
        PostDetailDto detailDto = postService.update(postId, dto);
        assertThat(detailDto).satisfies(it -> {
            assertThat(it.getTitle()).isEqualTo("the practical approach");
        });
        verify(postRepository).save(argThat(it -> it.getTitle().toString().equals("the practical approach")));
        verify(postDao).update(argThat(it -> it.getTitle().equals("the practical approach")));
    }

    @Test
    void deletePost() throws Exception {
        String postId = PostId.generate().toString();
        postDao.insert(new PostDetailDto(postId, "goeo1066", "the optical computing", "looks good"));
        PostDetailDto dto = postService.delete(postId);
        assertThat(dto).satisfies(it -> {
            assertThat(dto.getAuthor()).isEqualTo("goeo1066");
        });
        verify(postRepository).delete(argThat(it -> it.toString().equals(postId)));
        verify(postDao).delete(argThat(it -> it.equals(postId)));
    }
}