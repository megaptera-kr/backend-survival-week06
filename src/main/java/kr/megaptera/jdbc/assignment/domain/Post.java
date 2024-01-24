package kr.megaptera.jdbc.assignment.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
public class Post {
    private String id;

    private String title;

    private String author;

    private String content;

    public Post(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
