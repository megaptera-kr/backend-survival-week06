package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentId {

    private String id;

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return id;
    }

}
