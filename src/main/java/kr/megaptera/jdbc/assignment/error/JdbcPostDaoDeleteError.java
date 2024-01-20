package kr.megaptera.jdbc.assignment.error;

public class JdbcPostDaoDeleteError extends RuntimeException {
    public JdbcPostDaoDeleteError(Exception e) {
        super(e);
    }
}
