package kr.megaptera.jdbc.assignment.model;

import com.github.f4b6a3.tsid.TsidCreator;

public record CommentId(Long value) {

    public static CommentId of(Long value) {
        return new CommentId(value);
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toLong());
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
