package kr.megaptera.jdbc.assignment.dtos.post;

import kr.megaptera.jdbc.assignment.model.Post;

public record GetPostDTO(String id, String title, String author, String content) {

    public static GetPostDTO of(Post post) {
        return new GetPostDTO(post.id().toString(), post.title(), post.author(), post.content());
    }

}
