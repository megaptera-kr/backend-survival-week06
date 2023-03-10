package kr.megaptera.jdbc.assignment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private CommentId id;

    private PostId postId;

    private String author;

    private String content;

    public Comment(PostId postId, String author, String content) {
        this.id = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

}
