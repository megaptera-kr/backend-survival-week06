package kr.megaptera.jdbc.assignment.application;

import kr.megaptera.jdbc.assignment.daos.PostDao;
import kr.megaptera.jdbc.assignment.domain.Post;
import kr.megaptera.jdbc.assignment.dtos.PostDto;
import kr.megaptera.jdbc.assignment.repositories.PostRepository;
import kr.megaptera.jdbc.assignment.utils.PostUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePostService {

    private final PostDao postDao;

    public void createPost(PostDto postDto) {
        log.info("postDto: {}", postDto);
        Post post = Post.builder()
                .id(PostUtil.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .author(postDto.getAuthor())
                .build();
        postDao.createPostSave(post);
    }

}
