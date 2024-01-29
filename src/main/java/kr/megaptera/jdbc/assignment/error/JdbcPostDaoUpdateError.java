package kr.megaptera.jdbc.assignment.error;

public class JdbcPostDaoUpdateError extends RuntimeException {
    public JdbcPostDaoUpdateError(Exception e) {
        super(e);
    }
}
