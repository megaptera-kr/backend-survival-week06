package kr.megaptera.jdbc.assignment.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("NotFound");
    }
    public NotFoundException(Exception ex) {
        super(ex);
    }
}
