package kr.megaptera.jdbc.assignment.repository;

import kr.megaptera.jdbc.assignment.exceptions.*;
import kr.megaptera.jdbc.assignment.models.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class CommentRepository {
    Map<CommentId, Comment> comments;

    public CommentRepository() {
        this.comments = new HashMap<>();
    }

    public List<Comment> findAll(PostId postId) {
        List<Comment> commentList = comments.values().stream()
                .filter(comment -> comment.postId().equals(postId))
                .toList();

        return commentList;
    }

    public Comment find(CommentId id, PostId postId) throws CommentNotFound {
        Comment comment = comments.get(id);

        if (comment == null || !comment.postId().equals(postId)) {
            throw new CommentNotFound();
        }

        return comment;
    }

    public void save(Comment comment) {
        comments.put(comment.id(), comment);
    }

    public void delete(CommentId commentId) {
        comments.remove(commentId);
    }
}
