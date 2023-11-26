package kr.megaptera.jdbc.assignment.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.megaptera.jdbc.assignment.models.Post;

import java.util.Objects;

public class PostDto {
    private String id;
    private String title;
    private String author;
    private String content;


    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(Post post) {
        if (post.id() != null) {
            this.id = post.id().toString();
        }
        this.title = post.title().toString();
        this.author = post.author().toString();
        this.content = post.content().toString();
    }


    @JsonCreator
    public PostDto(@JsonProperty("title") String title, @JsonProperty("content") String content) {
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id) && Objects.equals(title, postDto.title) && Objects.equals(author, postDto.author) && Objects.equals(content, postDto.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, content);
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
