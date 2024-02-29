package com.clinic.medinstitute.services.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Long id) {
        super("Record not found on Database. ID: " + id);
    }
    
}
