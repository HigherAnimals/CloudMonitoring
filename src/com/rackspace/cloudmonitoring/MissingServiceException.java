package com.rackspace.cloudmonitoring;

public class MissingServiceException extends Exception {
    public MissingServiceException() {
        super();
    }

    public MissingServiceException(String message) {
        super(message);
    }
}
