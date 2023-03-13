package kr.megaptera.jdbc.assignment.models.post;

import kr.megaptera.jdbc.assignment.dtos.post.*;

public class Post {
    private PostId id;
    private String title;
    private String author;
    private MultilineText content;

    public void update(PostUpdateDto postUpdateDto) {
        this.title = postUpdateDto.getTitle();
        this.content = MultilineText.of(postUpdateDto.getContent());
    }

    public Post(PostId id, String title, String author, MultilineText content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, MultilineText content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }


}
