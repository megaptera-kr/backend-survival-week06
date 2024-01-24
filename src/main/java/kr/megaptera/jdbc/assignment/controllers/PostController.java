package kr.megaptera.jdbc.assignment.controllers;

import kr.megaptera.jdbc.assignment.application.*;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.exceptions.PostNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@CrossOrigin
public class PostController {

    private final CreatePostService createPostService;
    private final GetPostService getPostService;
    private final GetPostsService getPostsService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    /**
     * 게시글 조회
     *
     * @param
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPostList() {
        return getPostsService.getPostList();
    }

    /**
     * 게시글 상세 조회
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable String id) throws PostNotFound {
        return getPostService.getPost(id);
    }

    /**
     * 게시글 생성
     *
     * @param postDto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto postDto) {
        createPostService.createPost(postDto);
    }

    /**
     * 게시글 수정
     * @param id
     * @param postDto
     * @return
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable("id") String id,
                           @RequestBody PostDto postDto) throws PostNotFound {
        updatePostService.updatePost(id, postDto);
    }

    /**
     * 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PostDto deletePost(@PathVariable("id") String id) throws PostNotFound {
        return deletePostService.deletePost(id);
    }
}
