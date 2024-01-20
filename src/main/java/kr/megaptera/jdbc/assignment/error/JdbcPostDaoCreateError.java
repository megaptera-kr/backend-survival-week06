package kr.megaptera.jdbc.assignment.error;

public class JdbcPostDaoCreateError extends RuntimeException {
    public JdbcPostDaoCreateError(Exception e) {
        super(e);
    }
}
