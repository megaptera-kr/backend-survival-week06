package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

public class PostId {
    private String id;

    public PostId() {
        id = TsidCreator.getTsid().toString();
    }

    public PostId(String existId) {
        id = existId;
    }

    @Override
    public String toString() {
        return id;
    }
}
