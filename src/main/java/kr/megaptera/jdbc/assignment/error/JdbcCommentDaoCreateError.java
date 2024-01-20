package kr.megaptera.jdbc.assignment.error;

public class JdbcCommentDaoCreateError extends RuntimeException {
    public JdbcCommentDaoCreateError(Exception e) {
        super(e);
    }
}
