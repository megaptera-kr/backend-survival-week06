package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

public class CommentId {
    private String id;

    public CommentId() {
        id = TsidCreator.getTsid().toString();
    }

    public CommentId(String existId) {
        id = existId;
    }

    @Override
    public String toString() {
        return id;
    }
}
