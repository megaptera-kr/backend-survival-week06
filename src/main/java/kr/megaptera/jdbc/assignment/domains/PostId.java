package kr.megaptera.jdbc.assignment.domains;

import com.github.f4b6a3.tsid.*;

public class PostId {
    private String id;

    public PostId(String id) {
        this.id = id;
    }


    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    public static PostId of(String id) {
        return new PostId(id);
    }

    @Override
    public String toString() {
        return this.id;
    }
}
