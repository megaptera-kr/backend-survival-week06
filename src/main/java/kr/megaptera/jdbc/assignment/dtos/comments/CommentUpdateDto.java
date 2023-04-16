package kr.megaptera.jdbc.assignment.dtos.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateDto {
    private String content;
}
