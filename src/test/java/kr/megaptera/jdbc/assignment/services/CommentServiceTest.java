package kr.megaptera.jdbc.assignment.services;

import kr.megaptera.jdbc.assignment.daos.CommentDao;
import kr.megaptera.jdbc.assignment.dtos.CommentCreateDto;
import kr.megaptera.jdbc.assignment.dtos.CommentEntityDto;
import kr.megaptera.jdbc.assignment.dtos.CommentUpdateDto;
import kr.megaptera.jdbc.assignment.models.CommentId;
import kr.megaptera.jdbc.assignment.models.PostId;
import kr.megaptera.jdbc.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @SpyBean
    private CommentDao commentDao;

    @SpyBean
    private CommentRepository commentRepository;

    @Test
    void list() {
        List<String> postIdList = new ArrayList<>(2);
        Map<String, List<String>> postIdCommentIdMap = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            String postId = PostId.generate().toString();
            postIdList.add(postId);
            postIdCommentIdMap.put(postId, new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                String commentId = CommentId.generate().toString();
                postIdCommentIdMap.get(postId).add(commentId);
                commentDao.insert(new CommentEntityDto(commentId, postId, "goeo1066", "content" + i + ", " + j));
            }
        }

        commentService.list(postIdList.get(0));

        verify(commentRepository).findAllByPostId(argThat(it -> it.toString().equals(postIdList.get(0))));
        verify(commentDao).selectAllByPostId(argThat(it -> it.equals(postIdList.get(0))));
    }

    @Test
    void create() {
        String postId = PostId.generate().toString();
        CommentCreateDto dto = new CommentCreateDto("goeo1066", "content");
        commentService.create(postId, dto);
        verify(commentRepository).save(argThat(it -> it.getPostId().toString().equals(postId)));
        verify(commentDao).insert(argThat(it -> it.getPostId().equals(postId)));
    }

    @Test
    void update() {
        String commentId = CommentId.generate().toString();
        String postId = PostId.generate().toString();
        commentDao.insert(new CommentEntityDto(commentId, postId, "goeo1066", "old content"));
        commentService.update(commentId, new CommentUpdateDto("new content"), postId);

        verify(commentRepository).save(argThat(it -> it.getContent().toString().equals("new content")));
        verify(commentDao).update(argThat(it -> it.getContent().equals("new content")));
    }

    @Test
    void deleteComment() {
        String commentId = CommentId.generate().toString();
        String postId = PostId.generate().toString();
        commentDao.insert(new CommentEntityDto(commentId, postId, "goeo1066", "old content"));
        commentService.delete(commentId, postId);

        verify(commentRepository).delete(argThat(it -> it.toString().equals(commentId)), argThat(it -> it.toString().equals(postId)));
        verify(commentDao).delete(argThat(it -> it.equals(commentId)), argThat(it -> it.equals(postId)));
    }
}