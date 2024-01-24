package kr.megaptera.jdbc.assignment.repositories;

import kr.megaptera.jdbc.assignment.domain.Comment;
import kr.megaptera.jdbc.assignment.exceptions.CommentNotFound;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {

    Map<String, Comment> commentMap;

    public CommentRepository() {
        this.commentMap = new HashMap<>();
    }

    public List<Comment> findAll() {
        return new ArrayList<>(commentMap.values());
    }

    public Comment save(Comment comment) {
        return commentMap.put(comment.getId(), comment);
    }

    public Comment find(String id, String postId) throws CommentNotFound {
        Comment comment = commentMap.get(id);
        if(comment == null || !comment.getPostId().equals(postId)){
            throw new CommentNotFound();
        }
        return comment;
    }

    public void delete(String id) {
        commentMap.remove(id);
    }
}
