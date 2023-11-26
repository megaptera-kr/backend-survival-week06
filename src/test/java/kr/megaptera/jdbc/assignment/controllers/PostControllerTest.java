package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostAuthor;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostControllerTest {
    private final int SAMPLE_POST_ID = 1;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostDao postDao;

    private final String POST_CONTROLLER_URL;

    public PostControllerTest(@Value("${local.server.port}") int port) {
        POST_CONTROLLER_URL = "http://localhost:" + port + "/posts";
    }

    private final Post SAMPLE_POST = new Post(
            PostTitle.of("제목"),
            PostAuthor.of("작성자"),
            MultilineText.of("내용")
    );


    @BeforeEach
    void setup() {
        postDao.clear();
        postDao.save(SAMPLE_POST);
    }

    @Test
    @DisplayName("GET /posts")
    void list() {
        List<PostDto> result = restTemplate.getForObject(POST_CONTROLLER_URL, List.class);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() {
        PostDto postDto = restTemplate.getForObject(POST_CONTROLLER_URL + "/" + SAMPLE_POST_ID, PostDto.class);
        assertThat(postDto).isNotNull();
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() {
        PostDto postDto = restTemplate.getForObject(POST_CONTROLLER_URL + "/" + SAMPLE_POST_ID + "Incorrect", PostDto.class);
        //restTemplate 내부에서 404에 대한 처리를 리턴 클래스 생성자를 통해서 Null로 채워진 DTO를 생성해서 리턴
        assertThat(postDto.getId()).isNull();
        assertThat(postDto.getTitle()).isNull();
        assertThat(postDto.getAuthor()).isNull();
        assertThat(postDto.getContent()).isNull();
    }

    @Test
    @DisplayName("POST /posts")
    @Transactional
    void create() {

        PostDto postDto = new PostDto("id", "title", "author", "createTestContent");

        restTemplate.postForLocation(POST_CONTROLLER_URL, postDto);

        String body = restTemplate.getForObject(POST_CONTROLLER_URL, String.class);

        assertThat(body).contains("createTestContent");
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() {

        PostDto postDto = new PostDto("updatedTitle", "updatedContent");
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        Post existPost = postDao.findAll().get(0);
        PostDto updatedPost = restTemplate.patchForObject(POST_CONTROLLER_URL + "/" + existPost.id().toString(), postDto, PostDto.class);

        assertThat(updatedPost.getTitle()).isEqualTo(postDto.getTitle());
        assertThat(updatedPost.getContent()).isEqualTo(postDto.getContent());
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() {
        PostDto postDto = new PostDto(postDao.findAll().get(0));

        restTemplate.delete(POST_CONTROLLER_URL + "/" + postDto.getId());

        List<PostDto> result = restTemplate.getForObject(POST_CONTROLLER_URL, List.class);

        assertThat(result.size()).isEqualTo(0);
    }
}
