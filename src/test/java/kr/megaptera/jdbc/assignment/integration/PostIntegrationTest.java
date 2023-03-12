package kr.megaptera.jdbc.assignment.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.jdbc.assignment.application.DeletePostService;
import kr.megaptera.jdbc.assignment.application.GetPostsService;
import kr.megaptera.jdbc.assignment.dtos.PostCreateRequestDto;
import kr.megaptera.jdbc.assignment.dtos.PostResponseDto;
import kr.megaptera.jdbc.assignment.dtos.PostUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static kr.megaptera.jdbc.assignment.utils.AppStringUtils.PATH_SEPERATOR;
import static kr.megaptera.jdbc.assignment.utils.AppStringUtils.POST_REQ_MAP;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostIntegrationTest {
  private static final int SETUP_ITEM_NUM = 5;
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private GetPostsService getPostsService;

  @Autowired
  private DeletePostService deletePostService;

  @Autowired
  private ObjectMapper objectMapper;

  private List<PostCreateRequestDto> postCreateRequestDtos = new ArrayList<>();
  private List<PostResponseDto> postResponseDtos = new ArrayList<>();
  private String titleSamplePrefix = "제목입니다";
  private String authorSamplePrefix = "글쓴이입니다";
  private String contentSamplePrefix = "내용입니다";

  public PostIntegrationTest() {
  }

  @BeforeEach
  public void setUp() throws Exception {
    deleteAll();


    postCreateRequestDtos = IntStream.range(1, SETUP_ITEM_NUM + 1).mapToObj((number) -> {
      PostCreateRequestDto postCreateRequestDto =
        new PostCreateRequestDto(titleSamplePrefix + number,
          authorSamplePrefix + number,
          contentSamplePrefix + number);
      try {
        MvcResult result = this.mockMvc.perform(
                                 post("/posts").contentType(MediaType.APPLICATION_JSON)
                                               .content(objectMapper.writeValueAsString(postCreateRequestDto)))
                                       .andReturn();
        PostResponseDto postResponseDto =
          objectMapper.readValue(result.getResponse().getContentAsString(), PostResponseDto.class);
        postResponseDtos.add(postResponseDto);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      return postCreateRequestDto;
    }).collect(Collectors.toList());
  }

  private void deleteAll() {
    List<PostResponseDto> posts = getPostsService.getPosts();
    for (PostResponseDto post : posts
    ) {
      deletePostService.deletePost(post.getId());
    }
  }

  @Test
  @DisplayName("GET /posts")
    // 게시글 목록을 성공적으로 가져온다.
  void list() throws Exception {
    //given


    //when then
    this.mockMvc.perform(get("/posts")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(SETUP_ITEM_NUM)))
                // 첫번째 아이템이 title이라는 키를 포함하나?
                .andExpect(jsonPath("$[0]", hasKey("title")));
  }

  @Test
  @DisplayName("GET /posts/{id} - with correct ID")
  void detailWithCorrectId() throws Exception {
    // given

    // when
    this.mockMvc.perform(get("/posts/" + postResponseDtos.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasKey("title")));

    // then

  }

  @Test
  @DisplayName("GET /posts/{id} - with incorrect ID")
  void detail() throws Exception {
    // given

    // when
    this.mockMvc.perform(get(String.format("%s%s%d", POST_REQ_MAP, PATH_SEPERATOR, -1)))
                .andExpect(status().is4xxClientError());

    // then
  }

  @Test
  @DisplayName("POST /posts")
  void create() throws Exception {
    String title = "제목ㅋ";
    String author = "글쓴이ㅋ";
    String content = "내용ㅋ";
    PostCreateRequestDto postCreateRequestDto = new PostCreateRequestDto(title, author, content);
    this.mockMvc.perform(
          post(POST_REQ_MAP).contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(postCreateRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasKey("title")))
                .andExpect(jsonPath("$.title", equalTo(title)))
                .andExpect(jsonPath("$.author", equalTo(author)))
                .andExpect(jsonPath("$.content", equalTo(content)));
  }

  @Test
  @DisplayName("PATCH /posts/{id}")
  void update() throws Exception {
    String title = "업데이트 제목";
    String content = "업데이트된 내용";
    PostUpdateRequestDto postUpdateRequestDto = new PostUpdateRequestDto(title, content);
    final int UPDATE_TARGET_IDX = 0;
    final PostResponseDto targetPostResponseDto = postResponseDtos.get(UPDATE_TARGET_IDX);
    this.mockMvc.perform(
          patch(POST_REQ_MAP + PATH_SEPERATOR + targetPostResponseDto.getId()).contentType(
                                                                                MediaType.APPLICATION_JSON)
                                                                              .content(
                                                                                objectMapper.writeValueAsString(
                                                                                  postUpdateRequestDto)))
                .andExpect(status().isNoContent());
  }

  @Test
  @DisplayName("DELETE /posts/{id}")
  void deletePost() throws Exception {
    final int DELETE_TARGET_IDX = 0;
    final PostResponseDto targetPostResponseDto = postResponseDtos.get(DELETE_TARGET_IDX);
    this.mockMvc.perform(
          delete(POST_REQ_MAP + PATH_SEPERATOR + targetPostResponseDto.getId()))
                .andExpect(status().isNoContent());
  }
}
