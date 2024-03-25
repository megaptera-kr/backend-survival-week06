package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import kr.megaptera.jdbc.assignment.dtos.PostDto;

public class Post {
    private String id;
    private String title;
    private String content;
    private String author;

    public Post(String id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Post(PostDto postDto) {
        this.id = TsidCreator.getTsid().toString();
        this.title = postDto.getTitle();
        this.author = postDto.getAuthor();
        this.content = postDto.getContent();
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public String author() {
        return author;
    }
}
