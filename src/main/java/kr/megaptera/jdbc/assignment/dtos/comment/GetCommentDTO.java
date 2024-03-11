package kr.megaptera.jdbc.assignment.dtos.comment;

import kr.megaptera.jdbc.assignment.model.Comment;

public record GetCommentDTO(String id, String author, String content) {
    public static GetCommentDTO of(Comment comment) {
        return new GetCommentDTO(comment.id().toString(), comment.author(), comment.content());
    }
}
