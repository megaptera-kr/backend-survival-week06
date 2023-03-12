package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.entities.PostEntity;

import java.util.ArrayList;
import java.util.List;

public class PostResponseDto extends PostEntity {
  public static PostResponseDto of(PostEntity postEntity) {
    return new PostResponseDto(postEntity.getId(), postEntity.getTitle(), postEntity.getAuthor(),
      postEntity.getContent());
  }

  public static List<PostResponseDto> of(List<PostEntity> postEntities) {
    List<PostResponseDto> postResponseDtos = new ArrayList<>();
    for (PostEntity postEntity :
      postEntities) {
      postResponseDtos.add(of(postEntity));
    }
    return postResponseDtos;
  }

  public PostResponseDto(String id, String title, String author, String content) {
    super(id, title, author, content);
  }
}
