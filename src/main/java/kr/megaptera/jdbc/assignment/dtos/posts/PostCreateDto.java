package kr.megaptera.jdbc.assignment.dtos.posts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostCreateDto {
    private String title;
    private String author;
    private String content;
}
