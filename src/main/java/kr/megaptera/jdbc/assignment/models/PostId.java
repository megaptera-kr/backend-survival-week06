package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

public class PostId {
    private String id;

    private PostId(String id) {
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
        return id;
    }
}
