package kr.megaptera.jdbc.assignment.models.comment;

import kr.megaptera.jdbc.assignment.dtos.comment.*;
import kr.megaptera.jdbc.assignment.models.post.*;

public class Comment {
    private CommentId commentId;
    private PostId postId;
    private String author;
    private String content;

    public Comment(PostId postId, String author, String content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(CommentId commentId, PostId postId, String author, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentId commentId() {
        return commentId;
    }

    public PostId postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public void update(CommentUpdateDto commentUpdateDto) {
        this.content = commentUpdateDto.getContent();
    }
}
