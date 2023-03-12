package kr.megaptera.jdbc.assignment.daos;

import java.util.List;
import kr.megaptera.jdbc.assignment.models.Post;
import kr.megaptera.jdbc.assignment.models.PostId;
import org.springframework.stereotype.Component;

@Component
public interface PostDao {
  Post getById(PostId id);
  List<Post> getAll();
  void save(Post post);
  void update(Post post);
  void delete(Post post);
}
