package kr.megaptera.jdbc.assignment.utils;

import java.util.UUID;

public class PostUtil {

    public static String getId(){
        return UUID.randomUUID().toString();
    }
}
