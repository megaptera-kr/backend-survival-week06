package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import kr.megaptera.jdbc.assignment.dtos.CommentDto;

public class Comment {
    private String postId;
    private String id;
    private String author;
    private String content;

    public Comment() {
    }

    public Comment(CommentDto commentDto) {
        this.postId = commentDto.getPostId();
        this.id = TsidCreator.getTsid().toString();
        this.author = commentDto.getAuthor();
        this.content = commentDto.getContent();
    }

    public Comment(String postId, String id, String author, String content) {
        this.postId = postId;
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String postId() {
        return postId;
    }

    public String id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }
}
