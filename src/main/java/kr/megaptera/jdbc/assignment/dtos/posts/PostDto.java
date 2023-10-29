package kr.megaptera.jdbc.assignment.dtos.posts;

import kr.megaptera.jdbc.assignment.models.posts.Post;

public class PostDto {
    String id;
    String title;
    String author;
    String content;

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public static PostDto of(Post newPost) {
        return new PostDto(
                newPost.getId().toString(),
                newPost.getTitle(),
                newPost.getAuthor(),
                newPost.getContent()
        );
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
