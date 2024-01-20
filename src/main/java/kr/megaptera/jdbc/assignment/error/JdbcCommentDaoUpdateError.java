package kr.megaptera.jdbc.assignment.error;

public class JdbcCommentDaoUpdateError extends RuntimeException {
    public JdbcCommentDaoUpdateError(Exception e) {
        super(e);
    }
}
