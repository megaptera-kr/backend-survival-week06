package kr.megaptera.jdbc.assignment.error;

public class JdbcCommentDaoDeleteError extends RuntimeException {
    public JdbcCommentDaoDeleteError(Exception e) {
        super(e);
    }
}
