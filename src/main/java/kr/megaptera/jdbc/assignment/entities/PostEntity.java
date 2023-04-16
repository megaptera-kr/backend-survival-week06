package kr.megaptera.jdbc.assignment.entities;

import kr.megaptera.jdbc.assignment.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity implements Cloneable {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostEntity(Post post){
        id = post.getId().toString();
        title = post.getTitle();
        author = post.getAuthor();
        content = post.getContent().toString();
    }

    @Override
    public PostEntity clone(){
        var newEntity = new PostEntity();
        copyProperties(this, newEntity);
        return newEntity;
    }
}
