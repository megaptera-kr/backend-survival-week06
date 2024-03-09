package kr.megaptera.jdbc.assignment.model;

public record Post(PostId id, String title, String author, String content) {

    public Post(String title, String author, String content) {
        this(PostId.generate(), title, author, content);
    }

    public static Post copy(Post post, String title, String content) {
        String newTitle = title == null ? post.title : title;
        String newContent = content == null ? post.content : content;
        return new Post(post.id, newTitle, post.author, newContent);
    }
}
