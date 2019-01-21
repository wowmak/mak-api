package org.mak.error;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String exception)
    {
        super(exception);
    }
}
