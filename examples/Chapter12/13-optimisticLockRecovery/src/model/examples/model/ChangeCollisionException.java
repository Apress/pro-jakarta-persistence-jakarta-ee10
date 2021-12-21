package examples.model;

import jakarta.ejb.ApplicationException;

@ApplicationException
public class ChangeCollisionException extends RuntimeException {
    public ChangeCollisionException() { super(); }
}

