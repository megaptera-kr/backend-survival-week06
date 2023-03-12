package kr.megaptera.jdbc.assignment.dtos;

import kr.megaptera.jdbc.assignment.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String id;

    private String title;

    private String author;

    private String content;

    public PostDto(Post post) {
        this(post.getId().toString(), post.getTitle(), post.getAuthor(), post.getContent());
    }


}
