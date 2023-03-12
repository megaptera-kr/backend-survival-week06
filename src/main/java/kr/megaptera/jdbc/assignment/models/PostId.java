package kr.megaptera.jdbc.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

public class PostId {

    String postId;

    public PostId(String postId) {
        this.postId = postId;
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return postId;
    }

    public static PostId of(String postId) {
        return new PostId(postId);
    }
}
