package kr.megaptera.jdbc.assignment.model;

import com.github.f4b6a3.tsid.TsidCreator;

public record PostId(long value) {

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toLong());
    }

    public static PostId of(String value) {
        return new PostId(Long.parseLong(value));
    }

    public static PostId of(long value) {
        return new PostId(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
