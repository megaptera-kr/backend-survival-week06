package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostId {

    private String id;

    public static PostId of(String id) {
        return new PostId(id);
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return id;
    }

}
