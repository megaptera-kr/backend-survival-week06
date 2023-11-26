package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;
import kr.megaptera.jdbc.assignment.models.Comment;
import kr.megaptera.jdbc.assignment.models.CommentAuthor;
import kr.megaptera.jdbc.assignment.models.MultilineText;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostAuthor;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.models.PostTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {
    private final int SAMPLE_POST_ID = 1;
    private final int SAMPLE_COMMENT_ID = 1;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;

    private final String COMMENTS_CONTROLLER_URL;

    public CommentControllerTest(@Value("${local.server.port}") int port) {
        COMMENTS_CONTROLLER_URL = "http://localhost:" + port + "/comments";
    }

    private final Post SAMPLE_POST = new Post(
            PostTitle.of("제목"),
            PostAuthor.of("작성자"),
            MultilineText.of("내용")
    );
    private final Comment SAMPLE_COMMENT = new Comment(
            PostId.of(SAMPLE_POST_ID),
            CommentAuthor.of("commentAuthor"),
            MultilineText.of("commentContent")
    );


    @BeforeEach
    void setup() {
        postDao.clear();
        postDao.save(SAMPLE_POST);
        commentDao.clear();
        commentDao.save(SAMPLE_COMMENT);
    }

    @Test
    @DisplayName("GET /comments?postId={postId}")
    void list() {
        List<CommentDto> result = restTemplate.getForObject(COMMENTS_CONTROLLER_URL + "?postId=" + SAMPLE_POST_ID, List.class);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void create() {
        CommentDto commentDto = new CommentDto("createdAuthor", "createdContent");

        restTemplate.postForLocation(COMMENTS_CONTROLLER_URL + "?postId=" + SAMPLE_POST_ID, commentDto);

        String body = restTemplate.getForObject(COMMENTS_CONTROLLER_URL + "?postId=" + SAMPLE_POST_ID, String.class);
        assertThat(body).contains("createdAuthor");
    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void update() {
        CommentDto commentDto = new CommentDto("updatedContent");
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        CommentDto updatedComment = restTemplate.patchForObject(COMMENTS_CONTROLLER_URL + "/" + SAMPLE_COMMENT_ID + "?postId=" + SAMPLE_POST_ID
                , commentDto, CommentDto.class);

        assertThat(updatedComment.getId()).isEqualTo(String.valueOf(SAMPLE_COMMENT_ID));
        assertThat(updatedComment.getContent()).isEqualTo(commentDto.getContent());
    }

    @Test
    @DisplayName("DELETE /comments/{id}?postId={postId}")
    void deletePost() {
        restTemplate.delete(COMMENTS_CONTROLLER_URL + "/" + SAMPLE_COMMENT_ID + "?postId=" + SAMPLE_POST_ID);

        List<CommentDto> result = restTemplate.getForObject(COMMENTS_CONTROLLER_URL + "?postId=" + SAMPLE_POST_ID, List.class);

        assertThat(result.size()).isEqualTo(0);
    }
}
